package org.university_professor_bff.clients;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.university_professor_bff.exceptions.*;

@Component
public class WebClientErrorHandler {


    public void handleHttpErrors(ClientResponse response) {

        if(response == null) {
            return;
        }

        if(response.statusCode().is4xxClientError()) {

            String errorMessage = response.bodyToMono(String.class).block();

            if(errorMessage == null) {
                errorMessage = "";
            }

            if(response.statusCode().equals(HttpStatus.UNAUTHORIZED)) {

                throw new UnauthorizedException("Login unsuccessful! Please enter valid credentials!");

            }else if(response.statusCode().equals(HttpStatus.BAD_REQUEST)) {

                throw new BadRequestException(errorMessage);

            } else if(response.statusCode().equals(HttpStatus.NOT_FOUND)) {

                throw new NotFoundException(errorMessage);

            } else if(response.statusCode().equals(HttpStatus.CONFLICT)) {

                throw new ConflictException(errorMessage);

            } else if(response.statusCode().equals(HttpStatus.UNPROCESSABLE_ENTITY)) {
                throw new UnprocessableEntityException(errorMessage);
            }

            throw new RuntimeException("Client Error: " + errorMessage);

        } else if (response.statusCode().is5xxServerError()) {

            String errorMessage = response.bodyToMono(String.class).block();

            if(errorMessage == null) {
                errorMessage = "";
            }

            if(response.statusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
                throw new InternalServerException(errorMessage);
            } else if (response.statusCode().equals(HttpStatus.SERVICE_UNAVAILABLE)) {
                throw new ServiceUnavailableException(errorMessage);
            }

            throw new RuntimeException("Server Error: " + errorMessage);

        }


    }


    public void handleErrorAndRedirectForChangePasswordException(ClientResponse response) {

        if(response == null) {
            return;
        }

        if(response.statusCode().is4xxClientError() || response.statusCode().is5xxServerError()) {
            throw new ConfirmPasswordChangeException();
        }

    }


    public void handleErrorAndRedirectForRegisterActivation(ClientResponse response) {

        if(response == null) {
            return;
        }

        if(response.statusCode().is4xxClientError() || response.statusCode().is5xxServerError()) {
            throw new RegisterActivationException();
        }

    }




}
