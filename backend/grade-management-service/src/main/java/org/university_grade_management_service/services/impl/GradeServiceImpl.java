package org.university_grade_management_service.services.impl;

import com.netflix.discovery.DiscoveryClient;
import io.github.resilience4j.retry.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import org.university_grade_management_service.dtos.AddGradeDto;
import org.university_grade_management_service.dtos.RegisterExamDto;
import org.university_grade_management_service.dtos.StudentGradeScoreDto;
import org.university_grade_management_service.dtos.StudentPreExamScoreDto;
import org.university_grade_management_service.exceptions.ExamNotPassedException;
import org.university_grade_management_service.exceptions.ExamNotRegisteredException;
import org.university_grade_management_service.exceptions.RouteNotFoundException;
import org.university_grade_management_service.exceptions.ServiceUnavailableCustomException;
import org.university_grade_management_service.model.Course;
import org.university_grade_management_service.model.Grade;
import org.university_grade_management_service.model.Period;
import org.university_grade_management_service.model.PreExamObligationScore;
import org.university_grade_management_service.repositories.GradeRepository;
import org.university_grade_management_service.services.*;
import org.university_grade_management_service.utils.JwtUtil;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GradeServiceImpl implements GradeService {

    private GradeRepository gradeRepository;
    private CourseService courseService;
    private StudentEnrollmentService studentEnrollmentService;
    private PreExamObligationScoreService preExamObligationScoreService;
    private SendNotificationsService sendNotificationsService;
    private WebClient userServiceWebClient;
    private Retry isExamRegisteredRetry;
    private JwtUtil jwtUtil;

    @Autowired
    public GradeServiceImpl(GradeRepository gradeRepository, CourseService courseService, StudentEnrollmentService studentEnrollmentService, PreExamObligationScoreService preExamObligationScoreService, SendNotificationsService sendNotificationsService, WebClient userServiceWebClient, Retry isExamRegisteredRetry, JwtUtil jwtUtil) {
        this.gradeRepository = gradeRepository;
        this.courseService = courseService;
        this.studentEnrollmentService = studentEnrollmentService;
        this.preExamObligationScoreService = preExamObligationScoreService;
        this.sendNotificationsService = sendNotificationsService;
        this.jwtUtil = jwtUtil;
        this.userServiceWebClient = userServiceWebClient;
        this.isExamRegisteredRetry = isExamRegisteredRetry;
    }



    public List<StudentGradeScoreDto> getGrades(String authHeader) {

        List<StudentGradeScoreDto> gradeScoreDtoList = new ArrayList<>();

        String jwt = authHeader.substring(7);
        String studentIndex = jwtUtil.extractStudentIndex(jwt);

        List<Grade> studentGrades = gradeRepository.findAllByStudentIndex(studentIndex);

        for(Grade grade : studentGrades) {

            Course course = grade.getCourse();

            StudentGradeScoreDto gradeScoreDto = new StudentGradeScoreDto(course.getName(), grade.getGrade(), grade.getExamPoints(), grade.getExamPeriod());
            List<StudentPreExamScoreDto> preExamScoreDtos = new ArrayList<>();


            List<PreExamObligationScore> preExamObligationScores = preExamObligationScoreService.getAllPreExamPointsForCourseAndStudentIndex(course, studentIndex);

            for(PreExamObligationScore preExamScore : preExamObligationScores) {

                StudentPreExamScoreDto score = new StudentPreExamScoreDto(preExamScore.getName(), preExamScore.getPointsScored());
                preExamScoreDtos.add(score);

            }

            gradeScoreDto.setPreExamScoreDtos(preExamScoreDtos);
            gradeScoreDtoList.add(gradeScoreDto);


        }

        return gradeScoreDtoList;

    }

    @Transactional
    public Grade addGrade(String authHeader, Long courseId, AddGradeDto gradeDto) {

        String studentIndex = gradeDto.getStudentIndex();
        Integer examPointsScored = gradeDto.getPointsScored();
        String examPeriod = gradeDto.getExamPeriod();

        Course course = courseService.validateProfessorCourse(authHeader, courseId);
        studentEnrollmentService.isStudentEnrolled(studentIndex, course);

        List<PreExamObligationScore> preExamObligationScoreList = preExamObligationScoreService.getAllPreExamPointsForCourseAndStudentIndex(course, studentIndex);

        int totalPoints = 0;

        for(PreExamObligationScore score : preExamObligationScoreList) {
            totalPoints += score.getPointsScored();
        }

        totalPoints += examPointsScored;

        if(totalPoints < 51) {
            throw new ExamNotPassedException("Student hasn't passed the exam because his total number of points is less than 51!");
        }

        Boolean isExamRegistered = isExamRegisteredRetry(authHeader, studentIndex, course.getName(), examPeriod);

        if(!isExamRegistered) {
            throw new ExamNotRegisteredException("Student hasn't registered the exam!");
        }

        studentEnrollmentService.setStatusToCompleted(course, studentIndex);
        Grade savedGradeEntity = createGrade(studentIndex, examPointsScored, totalPoints, examPeriod, course);

        sendNotificationsService.sendGradeInfoNotification(studentIndex, course.getName(), savedGradeEntity.getGrade(), examPointsScored, preExamObligationScoreList);

        return savedGradeEntity;

    }

    private Grade createGrade(String studentIndex, Integer examPoints, Integer totalPoints, String examPeriod, Course course) {

        int grade = 0;

        if(totalPoints >= 51 && totalPoints <= 60) {
            grade = 6;
        } else if (totalPoints >= 61 && totalPoints <= 70) {
            grade = 7;
        }else if (totalPoints >= 71 && totalPoints <= 80) {
            grade = 8;
        }else if (totalPoints >= 81 && totalPoints <= 90) {
            grade = 9;
        } else {
            grade = 10;
        }


        Grade gradeEntity = new Grade(studentIndex, grade, examPoints, Period.valueOf(examPeriod), course);
        return gradeRepository.save(gradeEntity);

    }


    private Boolean isExamRegisteredRetry(String authHeader, String studentIndex, String courseName, String examPeriod) {

        try {

            return Retry.decorateSupplier(isExamRegisteredRetry, () -> sendIsExamRegisteredRequest(authHeader, studentIndex, courseName, examPeriod)).get();

        } catch (Exception e) {

            if(e.getCause() instanceof ChangeSetPersister.NotFoundException) {
                throw new RouteNotFoundException("The route that is called doesn't exist!");
            } else {
                throw new ServiceUnavailableCustomException("Service unavailable! Please try again later!");
            }

        }

    }


    private Boolean sendIsExamRegisteredRequest(String authHeader, String studentIndex, String courseName, String examPeriod) {


        String uri = UriComponentsBuilder.fromUriString("/student/isExamRegistered")
                .queryParam("studentIndex", studentIndex)
                .queryParam("courseName", courseName)
                .queryParam("examPeriod", examPeriod)
                .build()
                .toUriString();

        Optional<Boolean> examRegisteredOptional = Optional.ofNullable(
                this.userServiceWebClient
                        .delete()
                        .uri(uri)
                        .header("Authorization", authHeader)
                        .retrieve()
                        .toEntity(new ParameterizedTypeReference<Boolean>() {})
                        .block()
                        .getBody());


        if(examRegisteredOptional.isPresent()) {
            return examRegisteredOptional.get();
        }

        return false;


    }



}
