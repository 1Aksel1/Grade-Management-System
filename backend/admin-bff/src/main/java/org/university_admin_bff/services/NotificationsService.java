package org.university_admin_bff.services;

import org.university_admin_bff.dtos.NotificationTypeDto;
import org.university_admin_bff.dtos.SavedNotification;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface NotificationsService {


    public List<NotificationTypeDto> getAllNotificationTypes(String authHeader);

    public NotificationTypeDto createNotificationType(String authHeader, NotificationTypeDto notificationTypeDto);

    public NotificationTypeDto updateNotificationType(Long id, String authHeader, NotificationTypeDto notificationTypeDto);

    public void deleteNotificationTypeById(Long id, String authHeader);

    public List<SavedNotification> searchSavedNotifications(String authHeader, HttpServletRequest httpServletRequest);

}
