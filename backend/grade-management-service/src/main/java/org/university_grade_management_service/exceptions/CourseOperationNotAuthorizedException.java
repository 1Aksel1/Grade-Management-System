package org.university_grade_management_service.exceptions;

public class CourseOperationNotAuthorizedException extends RuntimeException{

    public CourseOperationNotAuthorizedException(String message) {
        super(message);
    }


}
