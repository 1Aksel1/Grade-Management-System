package org.university.user_service.exceptions;

public class BadRequestException extends RuntimeException {

    public BadRequestException() {
        super("Bad request");
    }
}
