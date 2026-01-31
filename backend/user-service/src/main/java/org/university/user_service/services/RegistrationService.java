package org.university.user_service.services;

import org.university.user_service.model.UserType;

public interface RegistrationService {

    public void createRegistration(Long userId, String name, String surname, UserType userType, String email);

    public void activateUser(String activationLink);

    public void removeExpiredRegistrations();

    public void resendRegistrationNotification(String cookie);

    public String getSessionIdForUserIdAndUserType(Long userId, UserType userType);


}
