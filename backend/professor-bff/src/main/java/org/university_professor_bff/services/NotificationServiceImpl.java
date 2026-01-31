package org.university_professor_bff.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.university_professor_bff.clients.NotificationServiceClient;
import org.university_professor_bff.dtos.SavedNotification;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    private NotificationServiceClient notificationServiceClient;

    @Autowired
    public NotificationServiceImpl(NotificationServiceClient notificationServiceClient) {
        this.notificationServiceClient = notificationServiceClient;
    }

    @Override
    public List<SavedNotification> searchSavedNotifications(String authHeader, HttpServletRequest httpServletRequest) {

        StringBuilder uriBuilder = new StringBuilder("/savedNotifications/professor");

        String queryParams = httpServletRequest.getQueryString();

        if(queryParams != null) {
            uriBuilder.append("?").append(queryParams);
        }

        return notificationServiceClient.searchSavedNotifications(uriBuilder.toString(), authHeader);

    }





}
