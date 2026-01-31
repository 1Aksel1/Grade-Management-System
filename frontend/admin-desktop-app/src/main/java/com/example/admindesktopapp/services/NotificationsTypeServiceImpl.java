package com.example.admindesktopapp.services;

import com.example.admindesktopapp.clients.NotificationServiceClient;
import com.example.admindesktopapp.dto.NotificationTypeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationsTypeServiceImpl implements NotificationsTypeService {

    private NotificationServiceClient notificationServiceClient;

    @Autowired
    public NotificationsTypeServiceImpl(NotificationServiceClient notificationServiceClient) {
        this.notificationServiceClient = notificationServiceClient;
    }

    @Override
    public List<NotificationTypeDto> getAllNotificationTypes() {
        return notificationServiceClient.getAllNotificationTypes();
    }

    @Override
    public NotificationTypeDto createNotificationType(String typeName, String subject, String template, String parameters) {

        if(typeName.equals("") || subject.equals("") || template.equals("") || parameters.equals("")) {
            throw new RuntimeException("Input fields can't be empty!");
        }

        return notificationServiceClient.createNotificationType(new NotificationTypeDto(null, typeName, subject, template, parameters));
    }

    @Override
    public void deleteNotificationType(Long id) {

        if(id == null || id <= 0) {
            throw new RuntimeException("Id must be a number bigger than 0!");
        }

        notificationServiceClient.deleteNotificationType(id);
    }


    @Override
    public NotificationTypeDto updateNotificationType(Long id, String typeName, String subject, String template, String parameters) {

        if(id == null) {
            throw new RuntimeException("Please select the notification type from the table you want to update!");
        }

        if(id <= 0 || typeName.equals("") || subject.equals("") || template.equals("") || parameters.equals("")) {
            throw new RuntimeException("Input fields can't be empty!");
        }

        return notificationServiceClient.updateNotificationType(new NotificationTypeDto(id, typeName, subject, template, parameters));
    }
}
