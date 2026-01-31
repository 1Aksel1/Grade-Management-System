package org.university_student_bff.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.university_student_bff.clients.GradeManagementServiceClient;
import org.university_student_bff.dtos.CourseGeneralInfoData;
import org.university_student_bff.dtos.RegisterExamDto;
import org.university_student_bff.dtos.SingleMessageDto;
import org.university_student_bff.dtos.StudentGradeScoreDto;

import java.util.List;

@Service
public class GradeManagementServiceImpl implements GradeManagementService {

    private GradeManagementServiceClient gradeManagementServiceClient;

    @Autowired
    public GradeManagementServiceImpl(GradeManagementServiceClient gradeManagementServiceClient) {
        this.gradeManagementServiceClient = gradeManagementServiceClient;
    }


    @Override
    public List<CourseGeneralInfoData> getEnrolledCourses(String authHeader) {

        String uri = "/courses/studentEnrolled";
        return gradeManagementServiceClient.getEnrolledCourses(uri, authHeader);

    }

    @Override
    public SingleMessageDto registerExam(String authHeader, RegisterExamDto registerExamDto) {

        String uri = "/registerExam";
        return gradeManagementServiceClient.registerExam(uri, authHeader, registerExamDto);

    }

    @Override
    public List<StudentGradeScoreDto> getStudentScores(String authHeader) {

        String uri = "/grade";
        return gradeManagementServiceClient.getStudentScores(uri, authHeader);

    }

}
