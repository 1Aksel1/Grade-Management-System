package org.university_professor_bff.services;

import org.university_professor_bff.dtos.SavedNotification;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface NotificationService {

    public List<SavedNotification> searchSavedNotifications(String authHeader, HttpServletRequest httpServletRequest);

}
