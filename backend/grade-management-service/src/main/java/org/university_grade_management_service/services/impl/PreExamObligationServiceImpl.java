package org.university_grade_management_service.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.university_grade_management_service.dtos.PreExamObligationDto;
import org.university_grade_management_service.exceptions.CourseOperationNotAuthorizedException;
import org.university_grade_management_service.exceptions.DuplicateEntityException;
import org.university_grade_management_service.exceptions.PreExamObligationNotFoundException;
import org.university_grade_management_service.exceptions.PreExamObligationScoreException;
import org.university_grade_management_service.model.Course;
import org.university_grade_management_service.model.PreExamObligation;
import org.university_grade_management_service.repositories.PreExamObligationRepository;
import org.university_grade_management_service.services.CourseService;
import org.university_grade_management_service.services.PreExamObligationService;
import org.university_grade_management_service.utils.JwtUtil;

import java.util.Optional;

@Service
public class PreExamObligationServiceImpl implements PreExamObligationService {

    private PreExamObligationRepository preExamObligationRepository;
    private JwtUtil jwtUtil;
    private CourseService courseService;

    @Autowired
    public PreExamObligationServiceImpl(PreExamObligationRepository preExamObligationRepository, CourseService courseService, JwtUtil jwtUtil) {
        this.courseService = courseService;
        this.jwtUtil = jwtUtil;
        this.preExamObligationRepository = preExamObligationRepository;
    }

    @Transactional
    public PreExamObligation addPreExamObligation(String authHeader, Long courseId, PreExamObligationDto preExamObligationDto) {

        String obligationName = preExamObligationDto.getName();
        Integer maxPoints = preExamObligationDto.getMaxPoints();

        Course course = courseService.validateProfessorCourse(authHeader, courseId);

        Optional<PreExamObligation> preExamObligationOptional = preExamObligationRepository.findByCourseAndName(course, obligationName);

        if(preExamObligationOptional.isPresent()) {
            throw new DuplicateEntityException("The pre exam obligation with this name already exists!");
        }

        return preExamObligationRepository.save(new PreExamObligation(obligationName, maxPoints, course));

    }


    public void validatePreExamObligationScore(Course course, String obligationName, Integer pointsScored) {

        Optional<PreExamObligation> preExamObligationOptional = preExamObligationRepository.findByCourseAndName(course, obligationName);

        if(!preExamObligationOptional.isPresent()) {
            throw new PreExamObligationNotFoundException("The pre exam obligation doesn't exist for the given name!");
        }

        PreExamObligation preExamObligation = preExamObligationOptional.get();

        if(pointsScored > preExamObligation.getMaxPoints()) {
            throw new PreExamObligationScoreException("The number of scored points can't be higher that the defined maximum number of points!");
        }


    }





}
