package org.university_admin_bff.clients;

import org.university_admin_bff.dtos.NotificationTypeDto;
import org.university_admin_bff.dtos.SavedNotification;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface NotificationsServiceClient {

    public List<NotificationTypeDto> getAllNotificationTypes(String uri, String authHeader);

    public NotificationTypeDto createNotificationType(String uri, String authHeader, NotificationTypeDto notificationTypeDto);

    public NotificationTypeDto updateNotificationType(String uri, String authHeader, NotificationTypeDto notificationTypeDto);

    public Void deleteNotificationTypeById(String uri, String authHeader);

    public List<SavedNotification> searchSavedNotifications(String uri, String authHeader);

}
