package org.university_grade_management_service.exceptions;

public class StudentEnrollmentNotActiveException extends RuntimeException{

    public StudentEnrollmentNotActiveException(String message) {
        super(message);
    }

}
