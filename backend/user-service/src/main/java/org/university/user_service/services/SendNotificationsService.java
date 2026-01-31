package org.university.user_service.services;

import org.university.user_service.model.UserType;

public interface SendNotificationsService {

    public void sendRegisterActivationNotification(String email, String name, String surname, UserType userType, String activationUUID);

    public void sendChangePasswordNotification(String email, String username, UserType userType, String confirmationUUID);


}
