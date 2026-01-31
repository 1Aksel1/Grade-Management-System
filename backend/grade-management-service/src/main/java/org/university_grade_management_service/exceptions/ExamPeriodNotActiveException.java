package org.university_grade_management_service.exceptions;

public class ExamPeriodNotActiveException extends RuntimeException{

    public ExamPeriodNotActiveException(String message) {
        super(message);
    }

}
