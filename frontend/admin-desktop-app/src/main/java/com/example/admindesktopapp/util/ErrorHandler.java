package com.example.admindesktopapp.util;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;

@Component
public class ErrorHandler {


    public void handleHttpErrors(ClientResponse response) {

        if(response == null) {
            return;
        }

        if(response.statusCode().is4xxClientError()) {
            String errorMessage = response.bodyToMono(String.class).block();

            if(errorMessage == null) {
                throw new RuntimeException();
            }

            throw new RuntimeException("Client Error: " + errorMessage);

        } else if (response.statusCode().is5xxServerError()) {
            String errorMessage = response.bodyToMono(String.class).block();

            if(errorMessage == null) {
                throw new RuntimeException();
            }

            throw new RuntimeException("Server Error: " + errorMessage);

        }


    }




}
