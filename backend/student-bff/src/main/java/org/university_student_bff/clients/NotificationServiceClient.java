package org.university_student_bff.clients;

import org.university_student_bff.dtos.SavedNotification;

import java.util.List;

public interface NotificationServiceClient {

    public List<SavedNotification> searchSavedNotifications(String uri, String authHeader);

}
