package org.university_grade_management_service.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.university_grade_management_service.exceptions.*;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException exception) {

        Map<String, String> errors = new HashMap<>();

        StringBuilder errorBuilder = new StringBuilder();

        for(ObjectError error: exception.getBindingResult().getAllErrors()){
            String fieldName = ((FieldError)error).getField();
            String errorMessage = error.getDefaultMessage();
            errorBuilder.append(fieldName + " : " + errorMessage);
            errorBuilder.append("\n");
        }

        errors.put("msg", errorBuilder.toString());

        return errors;

    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ExamPeriodNotActiveException.class, StudentEnrollmentNotActiveException.class, RegisterExamException.class, CourseOperationNotAuthorizedException.class, DuplicateEntityException.class, PreExamObligationScoreException.class})
    public Map<String, String> handleBadRequestException(Exception exception) {

        Map<String, String> errors = new HashMap<>();
        errors.put("msg", exception.getMessage());

        return errors;


    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({CourseNotFoundException.class, ExamPeriodNotFoundException.class, StudentEnrollmentNotFoundException.class, PreExamObligationNotFoundException.class, RouteNotFoundException.class})
    public Map<String, String> handleNotFoundException(Exception exception) {

        Map<String, String> errors = new HashMap<>();
        errors.put("msg", exception.getMessage());

        return errors;


    }

    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler()
    public Map<String, String> handleServiceUnavailableException(Exception exception) {

        Map<String, String> errors = new HashMap<>();
        errors.put("msg", exception.getMessage());

        return errors;


    }


    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler({ExamNotPassedException.class, ExamNotRegisteredException.class})
    public Map<String, String> handleUnprocessableContent(Exception exception) {

        Map<String, String> errors = new HashMap<>();
        errors.put("msg", exception.getMessage());

        return errors;


    }







}
