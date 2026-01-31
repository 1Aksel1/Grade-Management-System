package org.university_student_client_app.exceptions;

public class WrongCredentialsException extends RuntimeException{

    public WrongCredentialsException(String message) {
        super(message);
    }

}
