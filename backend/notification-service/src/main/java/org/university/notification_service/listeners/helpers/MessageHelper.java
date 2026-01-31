package org.university.notification_service.listeners.helpers;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class MessageHelper {

    private Validator validator;
    private Gson gson;

    @Autowired
    public MessageHelper(Validator validator, Gson gson) {
        this.validator = validator;
        this.gson = gson;
    }

    public <T> T getMessage(Message message, Class<T> clazz) throws RuntimeException, JMSException {

        String json = ((TextMessage) message).getText();
        T data = gson.fromJson(json, clazz);

        Set<ConstraintViolation<T>> violations = validator.validate(data);

        if(violations.isEmpty()) {
            return data;
        }

        printViolationsAndThrowExceptions(violations);
        return null;


    }


    public String createTextMessage(Object object) {

        try {
            return gson.toJson(object);
        }catch (JsonParseException jsonParseException) {
            throw new RuntimeException("Problem with creating the text message!");
        }

    }

    private <T> void printViolationsAndThrowExceptions(Set<ConstraintViolation<T>> violations) {

        String concatenatedViolations = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", "));
        throw new RuntimeException(concatenatedViolations);


    }


}
