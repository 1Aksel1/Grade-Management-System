package org.university_professor_bff.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.university_professor_bff.dtos.SavedNotification;

import java.util.List;

@Service
public class NotificationServiceClientImpl implements  NotificationServiceClient {

    private WebClient notificationServiceWebClient;
    private WebClientErrorHandler webClientErrorHandler;

    @Autowired
    public NotificationServiceClientImpl(WebClient notificationServiceWebClient, WebClientErrorHandler webClientErrorHandler) {
        this.notificationServiceWebClient = notificationServiceWebClient;
        this.webClientErrorHandler = webClientErrorHandler;
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
