package org.university_student_client_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.university_student_client_app.clients.NotificationServiceClient;
import org.university_student_client_app.dto.FilterParametersDto;
import org.university_student_client_app.dto.NotificationDto;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    private NotificationServiceClient notificationServiceClient;

    @Autowired
    public NotificationServiceImpl(NotificationServiceClient notificationServiceClient) {
        this.notificationServiceClient = notificationServiceClient;
    }

    @Override
    public List<NotificationDto> getStudentNotifications(String jwt, FilterParametersDto filterParametersDto) {
        return notificationServiceClient.getStudentNotifications(jwt, filterParametersDto);
    }

}
