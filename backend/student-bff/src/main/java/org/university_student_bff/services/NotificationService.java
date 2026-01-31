package org.university_student_bff.services;

import org.university_student_bff.dtos.SavedNotification;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface NotificationService {

    public List<SavedNotification> searchSavedNotifications(String authHeader, HttpServletRequest httpServletRequest);


}
