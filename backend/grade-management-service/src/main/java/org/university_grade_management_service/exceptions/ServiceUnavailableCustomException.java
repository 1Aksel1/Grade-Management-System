package org.university_grade_management_service.exceptions;

public class ServiceUnavailableCustomException extends RuntimeException{

    public ServiceUnavailableCustomException(String message) {
        super(message);
    }

}
