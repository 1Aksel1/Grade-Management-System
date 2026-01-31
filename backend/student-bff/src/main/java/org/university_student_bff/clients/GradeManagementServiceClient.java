package org.university_student_bff.clients;

import org.university_student_bff.dtos.CourseGeneralInfoData;
import org.university_student_bff.dtos.RegisterExamDto;
import org.university_student_bff.dtos.SingleMessageDto;
import org.university_student_bff.dtos.StudentGradeScoreDto;

import java.util.List;

public interface GradeManagementServiceClient {

    public List<CourseGeneralInfoData> getEnrolledCourses(String uri, String authHeader);

    public SingleMessageDto registerExam(String uri, String authHeader, RegisterExamDto registerExamDto);

    public List<StudentGradeScoreDto> getStudentScores(String uri, String authHeader);



}
