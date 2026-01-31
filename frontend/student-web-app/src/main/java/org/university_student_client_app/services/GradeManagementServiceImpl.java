package org.university_student_client_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.university_student_client_app.clients.GradeManagementServiceClient;
import org.university_student_client_app.clients.UserServiceClient;
import org.university_student_client_app.dto.CourseGeneralInfoData;
import org.university_student_client_app.dto.RegisterExamDto;
import org.university_student_client_app.dto.SingleMessageDto;
import org.university_student_client_app.dto.StudentGradeScoreDto;
import org.university_student_client_app.utils.JwtUtil;

import java.util.List;

@Service
public class GradeManagementServiceImpl implements GradeManagementService {

    private GradeManagementServiceClient gradeManagementServiceClient;
    private UserServiceClient userServiceClient;

    @Autowired
    public GradeManagementServiceImpl(GradeManagementServiceClient gradeManagementServiceClient, UserServiceClient userServiceClient) {
        this.gradeManagementServiceClient = gradeManagementServiceClient;
        this.userServiceClient = userServiceClient;
    }

    @Override
    public List<CourseGeneralInfoData> getEnrolledCourses(String jwt) {
        return gradeManagementServiceClient.getEnrolledCourses(jwt);
    }

    @Override
    public SingleMessageDto registerExam(String jwt, RegisterExamDto registerExamDto) {
        return gradeManagementServiceClient.registerExam(jwt, registerExamDto);
    }

    @Override
    public List<RegisterExamDto> getRegisteredExams(String jwt) {
        return userServiceClient.getRegisteredExams(jwt);
    }

    @Override
    public List<StudentGradeScoreDto> getStudentScored(String jwt) {
        return gradeManagementServiceClient.getStudentScores(jwt);
    }
}
