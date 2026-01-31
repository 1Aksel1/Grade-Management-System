package org.university_admin_bff.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.university_admin_bff.clients.NotificationsServiceClient;
import org.university_admin_bff.dtos.NotificationTypeDto;
import org.university_admin_bff.dtos.SavedNotification;
import org.university_admin_bff.utils.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class NotificationsServiceImpl implements NotificationsService {

    private NotificationsServiceClient notificationsServiceClient;

    @Autowired
    public NotificationsServiceImpl(NotificationsServiceClient notificationsServiceClient) {
        this.notificationsServiceClient = notificationsServiceClient;
    }

    @Override
    public List<NotificationTypeDto> getAllNotificationTypes(String authHeader) {

        String uri = "/notificationTypes";
        return notificationsServiceClient.getAllNotificationTypes(uri, authHeader);

    }

    @Override
    public NotificationTypeDto createNotificationType(String authHeader, NotificationTypeDto notificationTypeDto) {

        String uri = "/notificationTypes";
        return notificationsServiceClient.createNotificationType(uri, authHeader, notificationTypeDto);

    }

    @Override
    public NotificationTypeDto updateNotificationType(Long id, String authHeader, NotificationTypeDto notificationTypeDto) {

        String uri = "/notificationTypes/" + id;
        return notificationsServiceClient.updateNotificationType(uri, authHeader, notificationTypeDto);

    }

    @Override
    public void deleteNotificationTypeById(Long id, String authHeader) {

        String uri = "/notificationTypes/" + id;
        notificationsServiceClient.deleteNotificationTypeById(uri, authHeader);

    }


    @Override
    public List<SavedNotification> searchSavedNotifications(String authHeader, HttpServletRequest httpServletRequest) {

        StringBuilder uriBuilder = new StringBuilder("/savedNotifications/admin");

        String queryParams = httpServletRequest.getQueryString();

        if(queryParams != null) {
            uriBuilder.append("?").append(queryParams);
        }

        return notificationsServiceClient.searchSavedNotifications(uriBuilder.toString(), authHeader);

    }
}
