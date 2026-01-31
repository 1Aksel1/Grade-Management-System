package org.university.notification_service.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.university.notification_service.clients.EmailClient;
import org.university.notification_service.clients.UserServiceClient;
import org.university.notification_service.dtos.*;
import org.university.notification_service.model.NotificationType;
import org.university.notification_service.services.NotificationTypeService;
import org.university.notification_service.services.ProcessNotificationService;
import org.university.notification_service.services.SavedNotificationService;
import org.university.notification_service.validators.NotificationValidator;

import java.util.List;

@Service
public class ProcessNotificationServiceImpl implements ProcessNotificationService {

    private NotificationTypeService notificationTypeService;
    private SavedNotificationService savedNotificationService;
    private UserServiceClient userServiceClient;
    private NotificationValidator notificationValidator;
    private EmailClient emailClient;

    @Autowired
    public ProcessNotificationServiceImpl(NotificationTypeService notificationTypeService, SavedNotificationService savedNotificationService, EmailClient emailClient, NotificationValidator notificationValidator, UserServiceClient userServiceClient) {
        this.notificationTypeService = notificationTypeService;
        this.savedNotificationService = savedNotificationService;
        this.emailClient = emailClient;
        this.notificationValidator = notificationValidator;
        this.userServiceClient = userServiceClient;
    }

    public void processRegistrationActivation(ActivateRegistrationNotiDto data) {

        NotificationType notificationType = notificationTypeService.findByTypeName(data.getNotificationType());

        notificationValidator.validateRegistrationActivationParameters(notificationType.getParameters(), data);

        String email = data.getEmail();
        String subject = notificationType.getSubject();
        String content = String.format(notificationType.getTemplate(), data.getName(), data.getSurname(), data.getActivationLink());

        emailClient.sendEmail(email, subject, content);
        savedNotificationService.saveNotification(email, notificationType, subject, content);


    }


    public void processChangePasswordRequest(ChangePasswordNotiDto data) {

        NotificationType notificationType = notificationTypeService.findByTypeName(data.getNotificationType());

        notificationValidator.validateChangePasswordRequestParameters(notificationType.getParameters(), data);

        String email = data.getEmail();
        String subject = notificationType.getSubject();
        String content = String.format(notificationType.getTemplate(), data.getUsername(), data.getConfirmationLink());

        emailClient.sendEmail(email, subject, content);
        savedNotificationService.saveNotification(email, notificationType, subject, content);

    }


    public void processExamPeriodActivation(ExamPeriodActivatedNotiDto data) {

        NotificationType notificationType = notificationTypeService.findByTypeName(data.getNotificationType());
        List<StudentUsernameAndEmailDto> usernameAndEmailDataList = userServiceClient.sendRequestForExamPeriodActivationData();

        notificationValidator.validateExamPeriodActivationParameters(notificationType.getParameters(), data, usernameAndEmailDataList);

        for(StudentUsernameAndEmailDto usernameAndEmailDto : usernameAndEmailDataList) {

            String email = usernameAndEmailDto.getEmail();
            String subject = notificationType.getSubject();
            String content = String.format(notificationType.getTemplate(), usernameAndEmailDto.getUsername(), data.getExamPeriod());

            emailClient.sendEmail(email, subject, content);
            savedNotificationService.saveNotification(email, notificationType, subject, content);

        }

    }


    public void processGradeAdded(GradeInfoNotiDto data) {

        NotificationType notificationType = notificationTypeService.findByTypeName(data.getNotificationType());
        StudentNameAndEmailDto nameAndEmailData = userServiceClient.sendRequestForGradeAddedData(data.getStudentIndex());

        notificationValidator.validateGradeAddedParameters(notificationType.getParameters(), data, nameAndEmailData);

        String email = nameAndEmailData.getEmail();
        String subject = notificationType.getSubject();
        StringBuilder stringBuilder = new StringBuilder(String.format(notificationType.getTemplate(), nameAndEmailData.getName(), data.getCourseName(), data.getGrade(), data.getExamPoints()));

        for(StudentPreExamScoreDto preExamScoreDto : data.getPreExamScoreDtos()) {
            stringBuilder.append(preExamScoreDto);
        }

        String content = stringBuilder.toString();

        emailClient.sendEmail(email, subject, content);
        savedNotificationService.saveNotification(email, notificationType, subject, content);

    }


}
