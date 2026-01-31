package org.university_grade_management_service.exceptions;

public class ExamNotPassedException extends RuntimeException {

    public ExamNotPassedException(String message) {
        super(message);
    }

}
