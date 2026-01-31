package org.university_student_client_app.clients;

import org.university_student_client_app.dto.CourseGeneralInfoData;
import org.university_student_client_app.dto.RegisterExamDto;
import org.university_student_client_app.dto.SingleMessageDto;
import org.university_student_client_app.dto.StudentGradeScoreDto;

import java.util.List;

public interface GradeManagementServiceClient {

    public List<CourseGeneralInfoData> getEnrolledCourses(String jwt);

    public SingleMessageDto registerExam(String jwt, RegisterExamDto registerExamDto);

    public List<StudentGradeScoreDto> getStudentScores(String jwt);


}
