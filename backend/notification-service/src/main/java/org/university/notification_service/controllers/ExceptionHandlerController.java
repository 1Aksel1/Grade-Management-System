package org.university.notification_service.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.university.notification_service.exceptions.NotificationConstraintException;
import org.university.notification_service.exceptions.NotificationTypeNotFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerController {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
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
    @ExceptionHandler({MethodArgumentTypeMismatchException.class, MethodArgumentTypeMismatchException.class})
    public Map<String, String> handleBadRequestException(Exception exception) {

        Map<String, String> errors = new HashMap<>();
        errors.put("msg", "Please check the names and the values of query parameters!");

        return errors;

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotificationConstraintException.class)
    public Map<String, String> handleConstraintException(Exception exception) {

        Map<String, String> errors = new HashMap<>();
        errors.put("msg", exception.getMessage());

        return errors;

    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotificationTypeNotFoundException.class)
    public Map<String, String> handleNotFoundException(Exception exception) {

        Map<String, String> errors = new HashMap<>();
        errors.put("msg", exception.getMessage());

        return errors;


    }






}
