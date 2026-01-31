package org.university_grade_management_service.services;

import org.university_grade_management_service.dtos.PreExamObligationDto;
import org.university_grade_management_service.dtos.PreExamObligationScoreDto;
import org.university_grade_management_service.model.Course;
import org.university_grade_management_service.model.PreExamObligationScore;

import java.util.List;

public interface PreExamObligationScoreService {

    public PreExamObligationScore addPreExamObligationScore(String authHeader, Long courseId, PreExamObligationScoreDto preExamObligationScoreDto);

    public List<PreExamObligationScore> getAllPreExamPointsForCourseAndStudentIndex(Course course, String studentIndex);

}
