package org.university_grade_management_service.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.university_grade_management_service.dtos.PreExamObligationScoreDto;
import org.university_grade_management_service.exceptions.DuplicateEntityException;
import org.university_grade_management_service.exceptions.PreExamObligationNotFoundException;
import org.university_grade_management_service.model.Course;
import org.university_grade_management_service.model.PreExamObligationScore;
import org.university_grade_management_service.repositories.PreExamObligationScoreRepository;
import org.university_grade_management_service.services.CourseService;
import org.university_grade_management_service.services.PreExamObligationScoreService;
import org.university_grade_management_service.services.PreExamObligationService;
import org.university_grade_management_service.services.StudentEnrollmentService;

import java.util.List;
import java.util.Optional;

@Service
public class PreExamObligationScoreServiceImpl implements PreExamObligationScoreService {

    private PreExamObligationScoreRepository preExamObligationScoreRepository;
    private CourseService courseService;
    private PreExamObligationService preExamObligationService;
    private StudentEnrollmentService studentEnrollmentService;

    @Autowired
    public PreExamObligationScoreServiceImpl(PreExamObligationScoreRepository preExamObligationScoreRepository, CourseService courseService, StudentEnrollmentService studentEnrollmentService, PreExamObligationService preExamObligationService) {
        this.courseService = courseService;
        this.preExamObligationService = preExamObligationService;
        this.studentEnrollmentService = studentEnrollmentService;
        this.preExamObligationScoreRepository = preExamObligationScoreRepository;
    }



    @Transactional
    public PreExamObligationScore addPreExamObligationScore(String authHeader, Long courseId, PreExamObligationScoreDto preExamObligationScoreDto) {

        String obligationName = preExamObligationScoreDto.getName();
        String studentIndex = preExamObligationScoreDto.getStudentIndex();
        Integer pointsScored = preExamObligationScoreDto.getPointsScored();

        Course course = courseService.validateProfessorCourse(authHeader, courseId);
        studentEnrollmentService.isStudentEnrolled(studentIndex, course);
        preExamObligationService.validatePreExamObligationScore(course, obligationName, pointsScored);

        if(preExamObligationScoreRepository.existsByCourseAndNameAndStudentIndex(course, obligationName, studentIndex)) {
            throw new DuplicateEntityException("This pre exam score has already been saved for this student!");
        }

        return preExamObligationScoreRepository.save(new PreExamObligationScore(obligationName, studentIndex, pointsScored, course));

    }

    @Override
    public List<PreExamObligationScore> getAllPreExamPointsForCourseAndStudentIndex(Course course, String studentIndex) {
        return preExamObligationScoreRepository.findAllByCourseAndStudentIndex(course, studentIndex);
    }

}
