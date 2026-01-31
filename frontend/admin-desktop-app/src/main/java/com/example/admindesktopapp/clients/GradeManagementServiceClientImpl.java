package com.example.admindesktopapp.clients;

import com.example.admindesktopapp.dto.ExamPeriod;
import com.example.admindesktopapp.dto.ExamPeriodStatusDto;
import com.example.admindesktopapp.dto.SingleMessageDto;
import com.example.admindesktopapp.util.ErrorHandler;
import com.example.admindesktopapp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class GradeManagementServiceClientImpl implements GradeManagementServiceClient {

    private WebClient adminBffServiceClient;
    private JwtUtil jwtUtil;
    private ErrorHandler errorHandler;

    @Autowired
    public GradeManagementServiceClientImpl(WebClient adminBffServiceClient, JwtUtil jwtUtil, ErrorHandler errorHandler) {
        this.adminBffServiceClient = adminBffServiceClient;
        this.jwtUtil = jwtUtil;
        this.errorHandler = errorHandler;
    }


    @Override
    public List<ExamPeriod> getAllExamPeriods() {

        String uri = "/examPeriod/all";
        String authHeader = jwtUtil.getAuthHeader();

        ClientResponse response = this.adminBffServiceClient
                .get()
                .uri(uri)
                .header("Authorization", authHeader)
                .exchange()
                .block();

        errorHandler.handleHttpErrors(response);


        return response.bodyToFlux(ExamPeriod.class)
                .collectList()
                .block();

    }

    @Override
    public SingleMessageDto activateExamPeriod(ExamPeriodStatusDto examPeriodStatusDto) {

        String uri = "/examPeriod/activate";
        String authHeader = jwtUtil.getAuthHeader();

        ClientResponse response = this.adminBffServiceClient
                .put()
                .uri(uri)
                .header("Authorization", authHeader)
                .header("Content-Type", "application/json")
                .bodyValue(examPeriodStatusDto)
                .exchange()
                .block();

        errorHandler.handleHttpErrors(response);

        return response.bodyToMono(SingleMessageDto.class)
                .block();

    }

    @Override
    public SingleMessageDto deactivateExamPeriod(ExamPeriodStatusDto examPeriodStatusDto) {

        String uri = "/examPeriod/deactivate";
        String authHeader = jwtUtil.getAuthHeader();

        ClientResponse response = this.adminBffServiceClient
                .put()
                .uri(uri)
                .header("Authorization", authHeader)
                .header("Content-Type", "application/json")
                .bodyValue(examPeriodStatusDto)
                .exchange()
                .block();

        errorHandler.handleHttpErrors(response);

        return response.bodyToMono(SingleMessageDto.class)
                .block();
    }
}
