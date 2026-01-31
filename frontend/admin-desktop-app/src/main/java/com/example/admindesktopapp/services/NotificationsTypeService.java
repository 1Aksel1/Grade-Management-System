package com.example.admindesktopapp.services;

import com.example.admindesktopapp.dto.NotificationTypeDto;

import java.util.List;

public interface NotificationsTypeService {

    public List<NotificationTypeDto> getAllNotificationTypes();

    public NotificationTypeDto createNotificationType(String typeName, String subject, String template, String parameters);

    public void deleteNotificationType(Long id);

    public NotificationTypeDto updateNotificationType(Long id, String typeName, String subject, String template, String parameters);

}
