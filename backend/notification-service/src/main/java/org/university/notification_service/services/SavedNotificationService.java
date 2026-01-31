package org.university.notification_service.services;

import org.springframework.stereotype.Service;
import org.university.notification_service.model.NotificationType;
import org.university.notification_service.model.SavedNotification;

import java.time.LocalDateTime;
import java.util.List;

public interface SavedNotificationService {

    public void saveNotification(String email, NotificationType notificationType, String subject, String content);

    public List<SavedNotification> findAllSavedNotificationsForAdmin(String email, boolean activationEmailType, boolean passwordChangeType, boolean examPeriodActivatedType, boolean gradeAddedType, LocalDateTime fromDate, LocalDateTime toDate);

    public List<SavedNotification> findAllSavedNotificationsForStudent(String authHeader, boolean activationEmailType, boolean passwordChangeType, boolean examPeriodActivatedType, boolean gradeAddedType, LocalDateTime fromDate, LocalDateTime toDate);

    public List<SavedNotification> findAllSavedNotificationsForProfessor(String authHeader, boolean activationEmailType, boolean passwordChangeType, boolean examPeriodActivatedType, boolean gradeAddedType, LocalDateTime fromDate, LocalDateTime toDate);




}
