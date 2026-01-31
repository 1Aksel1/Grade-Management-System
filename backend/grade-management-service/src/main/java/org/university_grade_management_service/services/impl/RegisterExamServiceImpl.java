package org.university_grade_management_service.services.impl;

import io.github.resilience4j.retry.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.university_grade_management_service.dtos.RegisterExamDto;
import org.university_grade_management_service.dtos.RegisterExamRequestDto;
import org.university_grade_management_service.exceptions.RegisterExamException;
import org.university_grade_management_service.exceptions.RouteNotFoundException;
import org.university_grade_management_service.exceptions.ServiceUnavailableCustomException;
import org.university_grade_management_service.model.Course;
import org.university_grade_management_service.model.Period;
import org.university_grade_management_service.services.CourseService;
import org.university_grade_management_service.services.ExamPeriodService;
import org.university_grade_management_service.services.RegisterExamService;
import org.university_grade_management_service.services.StudentEnrollmentService;
import org.university_grade_management_service.utils.JwtUtil;

import java.util.Optional;

@Service
public class RegisterExamServiceImpl implements RegisterExamService {

    private CourseService courseService;
    private ExamPeriodService examPeriodService;
    private StudentEnrollmentService studentEnrollmentService;
    private WebClient userServiceWebClient;
    private JwtUtil jwtUtil;
    private Retry registerExamRetry;

    @Autowired
    public RegisterExamServiceImpl(CourseService courseService, ExamPeriodService examPeriodService, StudentEnrollmentService studentEnrollmentService, WebClient userServiceWebClient, JwtUtil jwtUtil, Retry registerExamRetry) {
        this.courseService = courseService;
        this.examPeriodService = examPeriodService;
        this.studentEnrollmentService = studentEnrollmentService;
        this.userServiceWebClient = userServiceWebClient;
        this.jwtUtil = jwtUtil;
        this.registerExamRetry = registerExamRetry;
    }

    @Transactional
    public void registerExam(String authHeader, RegisterExamRequestDto registerExamDto) {

        String jwt = authHeader.substring(7);

        Long studentId = jwtUtil.extractUserId(jwt);
        String studentIndex = jwtUtil.extractStudentIndex(jwt);
        String courseName = registerExamDto.getCourseName();
        Period examPeriod = Period.valueOf(registerExamDto.getExamPeriod());

        Course course = courseService.findCourseByName(courseName);
        examPeriodService.examPeriodIsActive(examPeriod);
        studentEnrollmentService.isStudentEnrolled(studentIndex, course);


        Boolean examRegistered = registerExamWithRetry(authHeader, studentId, courseName, examPeriod);

        if(!examRegistered){
            throw new RegisterExamException("Exam for the course " + courseName + " in the exam period " + examPeriod.toString() + " has already been registered!");
        }


    }


    private Boolean registerExamWithRetry(String authHeader, Long studentId, String courseName, Period examPeriod) {

        try {

            return Retry.decorateSupplier(registerExamRetry, () -> sendRegisterExamRequest(authHeader, studentId, courseName, examPeriod)).get();

        } catch (Exception e) {

            if(e.getCause() instanceof ChangeSetPersister.NotFoundException) {
                throw new RouteNotFoundException("The route that is called doesn't exist!");
            } else {
                throw new ServiceUnavailableCustomException("Service unavailable! Please try again later!");
            }

        }

    }


    private Boolean sendRegisterExamRequest(String authHeader, Long studentId, String courseName, Period examPeriod) {

        RegisterExamDto registerExamDto = new RegisterExamDto(studentId, courseName, examPeriod.toString());

            Optional<Boolean> examRegisteredOptional = Optional.ofNullable(
                    this.userServiceWebClient
                            .post()
                            .uri("/student/registerExam")
                            .header("Authorization", authHeader)
                            .body(BodyInserters.fromValue(registerExamDto))
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
