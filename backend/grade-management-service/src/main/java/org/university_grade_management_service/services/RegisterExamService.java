package org.university_grade_management_service.services;

import org.university_grade_management_service.dtos.RegisterExamRequestDto;

public interface RegisterExamService {


    public void registerExam(String authHeader, RegisterExamRequestDto registerExamDto);


}
