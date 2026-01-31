package org.university_student_bff.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.university_student_bff.dtos.CourseGeneralInfoData;
import org.university_student_bff.dtos.RegisterExamDto;
import org.university_student_bff.dtos.SingleMessageDto;
import org.university_student_bff.dtos.StudentGradeScoreDto;

import java.util.List;

@Service
public class GradeManagementServiceClientImpl implements GradeManagementServiceClient {

    private WebClient gradeManagementServiceWebClient;
    private WebClientErrorHandler webClientErrorHandler;

    @Autowired
    public GradeManagementServiceClientImpl(WebClient gradeManagementServiceWebClient, WebClientErrorHandler webClientErrorHandler) {
        this.gradeManagementServiceWebClient = gradeManagementServiceWebClient;
        this.webClientErrorHandler = webClientErrorHandler;
    }


    @Override
    public List<CourseGeneralInfoData> getEnrolledCourses(String uri, String authHeader) {

        ClientResponse response = this.gradeManagementServiceWebClient
                .get()
                .uri(uri)
                .header("Authorization", authHeader)
                .exchange().block();

        webClientErrorHandler.handleHttpErrors(response);

        return response.bodyToFlux(CourseGeneralInfoData.class)
                .collectList()
                .block();

    }

    @Override
    public SingleMessageDto registerExam(String uri, String authHeader, RegisterExamDto registerExamDto) {

        ClientResponse response = this.gradeManagementServiceWebClient
                .post()
                .uri(uri)
                .header("Authorization", authHeader)
                .header("Content-Type", "application/json")
                .bodyValue(registerExamDto)
                .exchange().block();

        webClientErrorHandler.handleHttpErrors(response);

        return response.bodyToMono(SingleMessageDto.class).block();

    }

    @Override
    public List<StudentGradeScoreDto> getStudentScores(String uri, String authHeader) {

        ClientResponse response = this.gradeManagementServiceWebClient
                .get()
                .uri(uri)
                .header("Authorization", authHeader)
                .exchange().block();

        webClientErrorHandler.handleHttpErrors(response);

        return response.bodyToFlux(StudentGradeScoreDto.class)
                .collectList()
                .block();

    }




}
