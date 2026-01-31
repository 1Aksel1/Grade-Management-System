package com.example.admindesktopapp.clients;

import com.example.admindesktopapp.dto.NotificationTypeDto;
import com.example.admindesktopapp.dto.SavedNotification;
import com.example.admindesktopapp.util.ErrorHandler;
import com.example.admindesktopapp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class NotificationServiceClientImpl implements NotificationServiceClient {

    private WebClient adminBffServiceClient;
    private JwtUtil jwtUtil;
    private ErrorHandler errorHandler;

    @Autowired
    public NotificationServiceClientImpl(WebClient adminBffServiceClient, JwtUtil jwtUtil, ErrorHandler errorHandler) {
        this.adminBffServiceClient = adminBffServiceClient;
        this.jwtUtil = jwtUtil;
        this.errorHandler = errorHandler;
    }

    @Override
    public List<SavedNotification> searchSavedNotifications(String uriWithQueryParams) {

        String authHeader = jwtUtil.getAuthHeader();

        ClientResponse response = this.adminBffServiceClient
                .get()
                .uri(uriWithQueryParams)
                .header("Authorization", authHeader)
                .exchange()
                .block();

        errorHandler.handleHttpErrors(response);

        return  response.bodyToFlux(SavedNotification.class)
                .collectList()
                .block();

    }


    @Override
    public List<NotificationTypeDto> getAllNotificationTypes() {

        String uri = "/notificationTypes";
        String authHeader = jwtUtil.getAuthHeader();

        ClientResponse response = this.adminBffServiceClient
                .get()
                .uri(uri)
                .header("Authorization", authHeader)
                .exchange()
                .block();

        errorHandler.handleHttpErrors(response);

        return  response.bodyToFlux(NotificationTypeDto.class)
                .collectList()
                .block();


    }


    @Override
    public Void deleteNotificationType(Long id) {

        StringBuilder stringBuilder = new StringBuilder("/notificationTypes/");
        String uri = stringBuilder.append(id).toString();

        String authHeader = jwtUtil.getAuthHeader();

        ClientResponse response = this.adminBffServiceClient
                .delete()
                .uri(uri)
                .header("Authorization", authHeader)
                .exchange()
                .block();

        errorHandler.handleHttpErrors(response);

        return  response
                .bodyToMono(Void.class)
                .block();

    }

    @Override
    public NotificationTypeDto createNotificationType(NotificationTypeDto notificationTypeDto) {

        String uri = "/notificationTypes";
        String authHeader = jwtUtil.getAuthHeader();

        ClientResponse response = this.adminBffServiceClient
                .post()
                .uri(uri)
                .header("Authorization", authHeader)
                .header("Content-Type", "application/json")
                .bodyValue(notificationTypeDto)
                .exchange()
                .block();

        errorHandler.handleHttpErrors(response);

        return  response
                .bodyToMono(NotificationTypeDto.class)
                .block();
    }

    @Override
    public NotificationTypeDto updateNotificationType(NotificationTypeDto notificationTypeDto) {

        StringBuilder stringBuilder = new StringBuilder("/notificationTypes/");
        String uri = stringBuilder.append(notificationTypeDto.getId()).toString();

        String authHeader = jwtUtil.getAuthHeader();

        ClientResponse response = this.adminBffServiceClient
                .put()
                .uri(uri)
                .header("Authorization", authHeader)
                .header("Content-Type", "application/json")
                .bodyValue(notificationTypeDto)
                .exchange()
                .block();

        errorHandler.handleHttpErrors(response);

        return  response
                .bodyToMono(NotificationTypeDto.class)
                .block();
    }
}
