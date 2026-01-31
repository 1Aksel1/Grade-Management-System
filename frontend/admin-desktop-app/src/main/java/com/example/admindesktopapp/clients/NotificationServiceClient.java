package com.example.admindesktopapp.clients;

import com.example.admindesktopapp.dto.NotificationTypeDto;
import com.example.admindesktopapp.dto.SavedNotification;

import java.util.List;

public interface NotificationServiceClient {

    public List<SavedNotification> searchSavedNotifications(String uriWithQueryParams);

    public List<NotificationTypeDto> getAllNotificationTypes();

    public NotificationTypeDto createNotificationType(NotificationTypeDto notificationTypeDto);

    public Void deleteNotificationType(Long id);

    public NotificationTypeDto updateNotificationType(NotificationTypeDto notificationTypeDto);



}
