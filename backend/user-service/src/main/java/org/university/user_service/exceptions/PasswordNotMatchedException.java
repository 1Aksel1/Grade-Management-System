package org.university.user_service.exceptions;

public class PasswordNotMatchedException extends RuntimeException{

    public PasswordNotMatchedException() {
        super("The value of the old password is not valid!");
    }
}
