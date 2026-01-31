package org.university_student_client_app.services;

import org.springframework.stereotype.Service;
import org.university_student_client_app.dto.CourseGeneralInfoData;
import org.university_student_client_app.dto.RegisterExamDto;
import org.university_student_client_app.dto.SingleMessageDto;
import org.university_student_client_app.dto.StudentGradeScoreDto;

import java.util.List;

public interface GradeManagementService {

    public List<CourseGeneralInfoData> getEnrolledCourses(String jwt);

    public SingleMessageDto registerExam(String jwt, RegisterExamDto registerExamDto);

    public List<RegisterExamDto> getRegisteredExams(String jwt);

    public List<StudentGradeScoreDto> getStudentScored(String jwt);

}
