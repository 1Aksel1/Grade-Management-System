package org.university.notification_service.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.university.notification_service.clients.UserServiceClient;
import org.university.notification_service.model.NotificationType;
import org.university.notification_service.model.SavedNotification;
import org.university.notification_service.repositories.SavedNotificationRepository;
import org.university.notification_service.services.NotificationTypeService;
import org.university.notification_service.services.SavedNotificationService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SavedNotificationServiceImpl implements SavedNotificationService {

    private NotificationTypeService notificationTypeService;
    private SavedNotificationRepository savedNotificationRepository;
    private UserServiceClient userServiceClient;

    @Autowired
    public SavedNotificationServiceImpl(SavedNotificationRepository savedNotificationRepository, NotificationTypeService notificationTypeService, UserServiceClient userServiceClient) {
        this.savedNotificationRepository = savedNotificationRepository;
        this.notificationTypeService = notificationTypeService;
        this.userServiceClient = userServiceClient;
    }

    public void saveNotification(String email, NotificationType notificationType, String subject, String content) {
        SavedNotification savedNotification = new SavedNotification(email, notificationType, subject, content, LocalDateTime.now());
        savedNotificationRepository.save(savedNotification);
    }

    public List<SavedNotification> findAllSavedNotificationsForAdmin(String email, boolean activationEmailType, boolean passwordChangeType, boolean examPeriodActivatedType, boolean gradeAddedType, LocalDateTime fromDate, LocalDateTime toDate) {

        List<Long> notificationTypeIds = setNotificationTypeIds(activationEmailType, passwordChangeType, examPeriodActivatedType, gradeAddedType);
        return savedNotificationRepository.findAllForAdmin(email, notificationTypeIds, fromDate, toDate);

    }

    public List<SavedNotification> findAllSavedNotificationsForStudent(String authHeader, boolean activationEmailType, boolean passwordChangeType, boolean examPeriodActivatedType, boolean gradeAddedType, LocalDateTime fromDate, LocalDateTime toDate) {

        String studentEmail = userServiceClient.sendRequestForStudentEmail(authHeader);
        List<Long> notificationTypeIds = setNotificationTypeIds(activationEmailType, passwordChangeType, examPeriodActivatedType, gradeAddedType);
        return savedNotificationRepository.findAllForStudentOrProfessor(studentEmail, notificationTypeIds, fromDate, toDate);

    }


    public List<SavedNotification> findAllSavedNotificationsForProfessor(String authHeader, boolean activationEmailType, boolean passwordChangeType, boolean examPeriodActivatedType, boolean gradeAddedType, LocalDateTime fromDate, LocalDateTime toDate) {

        String professorEmail = userServiceClient.sendRequestForProfessorEmail(authHeader);
        List<Long> notificationTypeIds = setNotificationTypeIds(activationEmailType, passwordChangeType, examPeriodActivatedType, gradeAddedType);
        return savedNotificationRepository.findAllForStudentOrProfessor(professorEmail, notificationTypeIds, fromDate, toDate);

    }

    private List<Long> setNotificationTypeIds(boolean activationEmailType, boolean passwordChangeType, boolean examPeriodActivatedType, boolean gradeAddedType) {

        List<Long> notificationTypeIds = new ArrayList<>();

        if(activationEmailType) {
            notificationTypeIds.add(notificationTypeService.findByTypeName("activation_email").getId());
        }

        if(passwordChangeType) {
            notificationTypeIds.add(notificationTypeService.findByTypeName("password_change").getId());
        }

        if(examPeriodActivatedType) {
            notificationTypeIds.add(notificationTypeService.findByTypeName("exam_period_activated").getId());
        }

        if(gradeAddedType) {
            notificationTypeIds.add(notificationTypeService.findByTypeName("grade_added").getId());
        }


        return notificationTypeIds;

    }

}
