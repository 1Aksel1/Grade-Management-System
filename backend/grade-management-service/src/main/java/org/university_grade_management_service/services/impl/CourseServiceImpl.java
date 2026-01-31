package org.university_grade_management_service.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.university_grade_management_service.dtos.CourseGeneralInfoDto;
import org.university_grade_management_service.exceptions.CourseNotFoundException;
import org.university_grade_management_service.exceptions.CourseOperationNotAuthorizedException;
import org.university_grade_management_service.mappers.CourseMapper;
import org.university_grade_management_service.model.*;
import org.university_grade_management_service.repositories.*;
import org.university_grade_management_service.services.CourseService;
import org.university_grade_management_service.services.StudentEnrollmentService;
import org.university_grade_management_service.utils.JwtUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private CourseRepository courseRepository;
    private PreExamObligationRepository preExamObligationRepository;
    private PreExamObligationScoreRepository preExamObligationScoreRepository;
    private StudentEnrollmentRepository studentEnrollmentRepository;
    private GradeRepository gradeRepository;
    private StudentEnrollmentService studentEnrollmentService;
    private CourseMapper courseMapper;
    private JwtUtil jwtUtil;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, JwtUtil jwtUtil, @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") CourseMapper courseMapper, StudentEnrollmentService studentEnrollmentService, PreExamObligationRepository preExamObligationRepository, PreExamObligationScoreRepository preExamObligationScoreRepository, GradeRepository gradeRepository, StudentEnrollmentRepository studentEnrollmentRepository) {
        this.courseRepository = courseRepository;
        this.preExamObligationRepository = preExamObligationRepository;
        this.preExamObligationScoreRepository = preExamObligationScoreRepository;
        this.gradeRepository = gradeRepository;
        this.studentEnrollmentRepository = studentEnrollmentRepository;
        this.jwtUtil = jwtUtil;
        this.courseMapper = courseMapper;
        this.studentEnrollmentService = studentEnrollmentService;
    }

    @Override
    public Course findCourseByName(String courseName) {
         Optional<Course> courseOptional = courseRepository.findByName(courseName);

        if(!courseOptional.isPresent()) {
            throw new CourseNotFoundException("Course with this name doesn't exist!");
        }

        return courseOptional.get();

    }

    @Override
    public Course findCourseById(Long id) {

        Optional<Course> courseOptional = courseRepository.findById(id);

        if(!courseOptional.isPresent()) {
            throw new CourseNotFoundException("Course with this id doesn't exist!");
        }

        return courseOptional.get();

    }


    @Override
    public Course validateProfessorCourse(String authHeader, Long courseId) {

        Course course = this.findCourseById(courseId);
        String jwt = authHeader.substring(7);

        if(!jwtUtil.extractCourses(jwt).contains(course.getName())) {
            throw new CourseOperationNotAuthorizedException("Professor is not teaching this course!");
        }

        return course;

    }


    @Override
    public List<CourseGeneralInfoDto> findAllProfessorCourses(String authHeader) {

        List<CourseGeneralInfoDto> courseListingDtos = new ArrayList<>();

        String jwt = authHeader.substring(7);
        List<String> professorCourseNames =  jwtUtil.extractCourses(jwt);

        for(String courseName : professorCourseNames) {

            Optional<Course> courseOptional = courseRepository.findByName(courseName);
            courseOptional.ifPresent(course -> courseListingDtos.add(courseMapper.toCourseGeneralInfoDtoFromEntity(course)));

        }

        return courseListingDtos;
    }


    @Override
    public CourseGeneralInfoDto getCourseGeneralInfoForId(String authHeader, Long courseId) {

        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        return optionalCourse.map(course -> courseMapper.toCourseGeneralInfoDtoFromEntity(course)).orElse(null);

    }

    @Override
    public List<PreExamObligation> getPreExamObligationsForCourseId(String authHeader, Long courseId) {

        Course course = this.findCourseById(courseId);
        return preExamObligationRepository.findAllByCourse(course);

    }

    @Override
    public List<PreExamObligationScore> getPreExamObligationsScoresForCourseId(String authHeader, Long courseId) {

        Course course = this.findCourseById(courseId);
        return preExamObligationScoreRepository.findAllByCourse(course);

    }

    @Override
    public List<StudentEnrollment> getStudentEnrollmentsForCourseId(String authHeader, Long courseId) {
        Course course = this.findCourseById(courseId);
        return studentEnrollmentRepository.findAllByCourse(course);
    }

    @Override
    public List<Grade> getGradesForCourseId(String authHeader, Long courseId) {
        Course course = this.findCourseById(courseId);
        return gradeRepository.findAllByCourse(course);
    }

    @Override
    public List<CourseGeneralInfoDto> findAllStudentActiveCourses(String authHeader) {

            List<CourseGeneralInfoDto> resultList = new ArrayList<>();

            String jwt = authHeader.substring(7);
            String studentIndex = jwtUtil.extractStudentIndex(jwt);

            List<StudentEnrollment> studentEnrollmentList = studentEnrollmentService.findAllActiveEnrollmentsForStudentIndex(studentIndex);
            List<Long> enrollmentIds = new ArrayList<>();

            studentEnrollmentList.forEach(studentEnrollment -> {
                enrollmentIds.add(studentEnrollment.getId());
            });

            List<Course> courseList = courseRepository.findCoursesByEnrollmentIds(enrollmentIds);

            courseList.forEach(course -> {
                resultList.add(courseMapper.toCourseGeneralInfoDtoFromEntity(course));
            });

            return resultList;


    }
}
