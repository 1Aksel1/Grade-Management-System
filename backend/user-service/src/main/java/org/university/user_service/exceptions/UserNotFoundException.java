package org.university.user_service.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("User with the given id doesn't exist!");
    }

}
