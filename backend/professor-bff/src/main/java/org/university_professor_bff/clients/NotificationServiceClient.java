package org.university_professor_bff.clients;

import org.university_professor_bff.dtos.SavedNotification;

import java.util.List;

public interface NotificationServiceClient {

    public List<SavedNotification> searchSavedNotifications(String uri, String authHeader);

}
