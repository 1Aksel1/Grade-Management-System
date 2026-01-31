package org.university_grade_management_service.services;

import org.university_grade_management_service.dtos.PreExamObligationDto;
import org.university_grade_management_service.model.Course;
import org.university_grade_management_service.model.PreExamObligation;

public interface PreExamObligationService {

    public PreExamObligation addPreExamObligation(String authHeader, Long courseId, PreExamObligationDto preExamObligationDto);

    public void validatePreExamObligationScore(Course course, String obligationName, Integer pointsScored);


}
