package org.university_grade_management_service.services;

import org.university_grade_management_service.dtos.AddGradeDto;
import org.university_grade_management_service.dtos.StudentGradeScoreDto;
import org.university_grade_management_service.model.Grade;

import java.util.List;

public interface GradeService {

    public Grade addGrade(String authHeader, Long courseId, AddGradeDto gradeDto);

    public List<StudentGradeScoreDto> getGrades(String authHeader);


}
