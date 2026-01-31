package org.university.user_service.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.university.user_service.dtos.ActivateRegistrationNotiDto;
import org.university.user_service.dtos.ChangePasswordNotiDto;
import org.university.user_service.helpers.MessageHelper;
import org.university.user_service.model.UserType;
import org.university.user_service.services.SendNotificationsService;

@Service
public class SendNotificationServiceImpl implements SendNotificationsService {

    private JmsTemplate jmsTemplate;
    private MessageHelper messageHelper;

    @Value("${destination.registerActivation}")
    private String registerActivationDestination;

    @Value("${notificationType.registerActivation}")
    private String registerActivationNotificationType;

    @Value("${destination.changePassword}")
    private String changePasswordDestination;

    @Value("${notificationType.changePassword}")
    private String changePasswordNotificationType;

    @Autowired
    public SendNotificationServiceImpl(JmsTemplate jmsTemplate, MessageHelper messageHelper) {
        this.jmsTemplate = jmsTemplate;
        this.messageHelper = messageHelper;
    }


    public void sendRegisterActivationNotification(String email, String name, String surname, UserType userType, String activationUUID) {

        StringBuilder stringBuilder = new StringBuilder("http://localhost:");


        if(userType.equals(UserType.STUDENT)) {
            stringBuilder.append("8082/student-bff/api/activate?token=");
        } else if(userType.equals(UserType.PROFESSOR)) {
            stringBuilder.append("8081/professor-bff/api/activate?token=");
        }


        stringBuilder.append(activationUUID);
        String fullActivationLink = stringBuilder.toString();

        ActivateRegistrationNotiDto activateRegistrationDto = new ActivateRegistrationNotiDto(email, registerActivationNotificationType, name, surname, fullActivationLink);
        String message = messageHelper.createTextMessage(activateRegistrationDto);

        jmsTemplate.convertAndSend(registerActivationDestination, message);


    }


    public void sendChangePasswordNotification(String email, String username, UserType userType, String confirmationUUID) {

        StringBuilder stringBuilder = new StringBuilder("http://localhost:");

        if(userType.equals(UserType.STUDENT)) {
            stringBuilder.append("8082/student-bff/api/profile/");
        } else if (userType.equals(UserType.PROFESSOR)) {
            stringBuilder.append("8081/professor-bff/api/profile/");
        } else if (userType.equals(UserType.ADMIN)) {
            stringBuilder.append("8080/admin-bff/api/profile/");
        }

        stringBuilder.append("confirmPasswordChange?token=");
        stringBuilder.append(confirmationUUID);
        String confirmationLink = stringBuilder.toString();

        ChangePasswordNotiDto changePasswordNotiDto = new ChangePasswordNotiDto(email, changePasswordNotificationType, username, confirmationLink);
        String message = messageHelper.createTextMessage(changePasswordNotiDto);

        jmsTemplate.convertAndSend(changePasswordDestination, message);



    }



}
