package org.university_student_client_app.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.university_student_client_app.dto.*;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class GradeManagementServiceClientImpl implements GradeManagementServiceClient {

    private WebClient studentBffServiceClient;

    @Autowired
    public GradeManagementServiceClientImpl(WebClient studentBffServiceClient) {
        this.studentBffServiceClient = studentBffServiceClient;
    }

    @Override
    public List<CourseGeneralInfoData> getEnrolledCourses(String jwt) {

        String uri = "/courses/studentEnrolled";

        StringBuilder stringBuilder = new StringBuilder("Bearer ");
        stringBuilder.append(jwt);

        String authHeader = stringBuilder.toString();


        return this.studentBffServiceClient
                .get()
                .uri(uri)
                .header("Authorization", authHeader)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> {
                    System.out.println("Client error");

                    return clientResponse.bodyToMono(String.class)
                            .flatMap(errorMessage -> Mono.error(new RuntimeException("Client Error: " + errorMessage)));
                })
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> {
                    System.out.println("Server error");

                    return clientResponse.bodyToMono(String.class)
                            .flatMap(errorMessage -> Mono.error(new RuntimeException("Server error: " + errorMessage)));
                })
                .bodyToFlux(CourseGeneralInfoData.class)
                .collectList()
                .block();

    }


    @Override
    public SingleMessageDto registerExam(String jwt, RegisterExamDto registerExamDto) {

        String uri = "/registerExam";

        StringBuilder stringBuilder = new StringBuilder("Bearer ");
        stringBuilder.append(jwt);

        String authHeader = stringBuilder.toString();


        return this.studentBffServiceClient
                .post()
                .uri(uri)
                .header("Authorization", authHeader)
                .bodyValue(registerExamDto)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> {
                    System.out.println("Client error");

                    return clientResponse.bodyToMono(String.class)
                            .flatMap(errorMessage -> Mono.error(new RuntimeException("Client Error: " + errorMessage)));
                })
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> {
                    System.out.println("Server error");

                    return clientResponse.bodyToMono(String.class)
                            .flatMap(errorMessage -> Mono.error(new RuntimeException("Server error: " + errorMessage)));
                })
                .bodyToMono(SingleMessageDto.class)
                .block();


    }

    @Override
    public List<StudentGradeScoreDto> getStudentScores(String jwt) {

        String uri = "/grade";

        StringBuilder stringBuilder = new StringBuilder("Bearer ");
        stringBuilder.append(jwt);

        String authHeader = stringBuilder.toString();

        return this.studentBffServiceClient
                .get()
                .uri(uri)
                .header("Authorization", authHeader)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> {
                    System.out.println("Client error");

                    return clientResponse.bodyToMono(String.class)
                            .flatMap(errorMessage -> Mono.error(new RuntimeException("Client Error: " + errorMessage)));
                })
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> {
                    System.out.println("Server error");

                    return clientResponse.bodyToMono(String.class)
                            .flatMap(errorMessage -> Mono.error(new RuntimeException("Server error: " + errorMessage)));
                })
                .bodyToFlux(StudentGradeScoreDto.class)
                .collectList()
                .block();

    }
}
