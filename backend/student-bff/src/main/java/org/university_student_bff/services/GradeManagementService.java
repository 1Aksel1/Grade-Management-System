package org.university_student_bff.services;

import org.university_student_bff.dtos.CourseGeneralInfoData;
import org.university_student_bff.dtos.RegisterExamDto;
import org.university_student_bff.dtos.SingleMessageDto;
import org.university_student_bff.dtos.StudentGradeScoreDto;

import java.util.List;

public interface GradeManagementService {

    public List<CourseGeneralInfoData> getEnrolledCourses(String authHeader);

    public SingleMessageDto registerExam(String authHeader, RegisterExamDto registerExamDto);

    public List<StudentGradeScoreDto> getStudentScores(String authHeader);


}
