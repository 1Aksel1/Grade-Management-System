package org.university_admin_bff.clients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.university_admin_bff.dtos.ExamPeriod;
import org.university_admin_bff.dtos.ExamPeriodStatusDto;
import org.university_admin_bff.dtos.SingleMessageDto;

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
    public List<ExamPeriod> getAllExamPeriods(String uri, String authHeader) {

        ClientResponse response = this.gradeManagementServiceWebClient
                .get()
                .uri(uri)
                .header("Authorization", authHeader)
                .exchange().block();

        webClientErrorHandler.handleHttpErrors(response);

        return response.bodyToFlux(ExamPeriod.class)
                .collectList()
                .block();

    }


    @Override
    public SingleMessageDto activateExamPeriod(String uri, String authHeader, ExamPeriodStatusDto examPeriodStatusDto) {

        ClientResponse response = this.gradeManagementServiceWebClient
                .put()
                .uri(uri)
                .header("Authorization", authHeader)
                .header("Content-Type", "application/json")
                .bodyValue(examPeriodStatusDto)
                .exchange().block();

        webClientErrorHandler.handleHttpErrors(response);

        return response.bodyToMono(SingleMessageDto.class).block();

    }

    @Override
    public SingleMessageDto deactivateExamPeriod(String uri, String authHeader, ExamPeriodStatusDto examPeriodStatusDto) {

        ClientResponse response = this.gradeManagementServiceWebClient
                .put()
                .uri(uri)
                .header("Authorization", authHeader)
                .header("Content-Type", "application/json")
                .bodyValue(examPeriodStatusDto)
                .exchange().block();

        webClientErrorHandler.handleHttpErrors(response);

        return response.bodyToMono(SingleMessageDto.class).block();

    }

}
