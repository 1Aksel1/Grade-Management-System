package org.university_admin_bff.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.university_admin_bff.exceptions.*;

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

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public Map<String, String> unauthorizedExceptionHandler(Exception exception) {

        Map<String, String> errors = new HashMap<>();
        errors.put("msg", exception.getMessage());

        return errors;

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public Map<String, String> badRequestExceptionHandler(Exception exception) {

        Map<String, String> errors = new HashMap<>();
        errors.put("msg", exception.getMessage());

        return errors;

    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public Map<String, String> notFoundExceptionHandler(Exception exception) {

        Map<String, String> errors = new HashMap<>();
        errors.put("msg", exception.getMessage());

        return errors;

    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ConflictException.class)
    public Map<String, String> conflictExceptionHandler(Exception exception) {

        Map<String, String> errors = new HashMap<>();
        errors.put("msg", exception.getMessage());

        return errors;

    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(UnprocessableEntityException.class)
    public Map<String, String> handleUnprocessableContent(Exception exception) {

        Map<String, String> errors = new HashMap<>();
        errors.put("msg", exception.getMessage());

        return errors;

    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InternalServerException.class)
    public Map<String, String> internalServerExceptionHandler(Exception exception) {

        Map<String, String> errors = new HashMap<>();
        errors.put("msg", exception.getMessage());

        return errors;

    }

    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(ServiceUnavailableException.class)
    public Map<String, String> serviceUnavailableExceptionHandler(Exception exception) {

        Map<String, String> errors = new HashMap<>();
        errors.put("msg", exception.getMessage());

        return errors;

    }


}
