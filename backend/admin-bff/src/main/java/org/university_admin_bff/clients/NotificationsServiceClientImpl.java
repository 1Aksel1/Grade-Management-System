package org.university_admin_bff.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.university_admin_bff.dtos.NotificationTypeDto;
import org.university_admin_bff.dtos.SavedNotification;

import java.util.List;

@Service
public class NotificationsServiceClientImpl implements NotificationsServiceClient {

    private WebClient notificationServiceWebClient;
    private WebClientErrorHandler webClientErrorHandler;

    @Autowired
    public NotificationsServiceClientImpl(WebClient notificationServiceWebClient, WebClientErrorHandler webClientErrorHandler) {
        this.notificationServiceWebClient = notificationServiceWebClient;
        this.webClientErrorHandler = webClientErrorHandler;
    }


    @Override
    public List<NotificationTypeDto> getAllNotificationTypes(String uri, String authHeader) {

        ClientResponse response = this.notificationServiceWebClient
                .get()
                .uri(uri)
                .header("Authorization", authHeader)
                .exchange().block();

        webClientErrorHandler.handleHttpErrors(response);

        return response.bodyToFlux(NotificationTypeDto.class)
                .collectList()
                .block();
    }

    @Override
    public NotificationTypeDto createNotificationType(String uri, String authHeader, NotificationTypeDto notificationTypeDto) {

        ClientResponse response = this.notificationServiceWebClient
                .post()
                .uri(uri)
                .header("Authorization", authHeader)
                .header("Content-Type", "application/json")
                .bodyValue(notificationTypeDto)
                .exchange().block();

        webClientErrorHandler.handleHttpErrors(response);

        return response.bodyToMono(NotificationTypeDto.class).block();

    }

    @Override
    public NotificationTypeDto updateNotificationType(String uri, String authHeader, NotificationTypeDto notificationTypeDto) {

        ClientResponse response = this.notificationServiceWebClient
                .put()
                .uri(uri)
                .header("Authorization", authHeader)
                .header("Content-Type", "application/json")
                .bodyValue(notificationTypeDto)
                .exchange().block();

        webClientErrorHandler.handleHttpErrors(response);

        return response.bodyToMono(NotificationTypeDto.class).block();
    }

    @Override
    public Void deleteNotificationTypeById(String uri, String authHeader) {

        ClientResponse response = this.notificationServiceWebClient
                .delete()
                .uri(uri)
                .header("Authorization", authHeader)
                .exchange().block();

        webClientErrorHandler.handleHttpErrors(response);

        return response.bodyToMono(Void.class).block();

    }

    @Override
    public List<SavedNotification> searchSavedNotifications(String uri, String authHeader) {

        ClientResponse response = this.notificationServiceWebClient
                .get()
                .uri(uri)
                .header("Authorization", authHeader)
                .exchange().block();

        webClientErrorHandler.handleHttpErrors(response);

        return response.bodyToFlux(SavedNotification.class)
                .collectList()
                .block();

    }
}
