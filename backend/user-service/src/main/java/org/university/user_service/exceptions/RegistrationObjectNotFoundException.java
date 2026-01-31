package org.university.user_service.exceptions;

public class RegistrationObjectNotFoundException extends RuntimeException{

    public RegistrationObjectNotFoundException() {
        super("The activation link is invalid and has expired!");
    }
}
