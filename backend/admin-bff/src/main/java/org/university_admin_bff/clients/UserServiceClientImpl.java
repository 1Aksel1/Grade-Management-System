package org.university_admin_bff.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.university_admin_bff.dtos.*;
import org.university_admin_bff.exceptions.ConflictException;
import org.university_admin_bff.exceptions.InternalServerException;
import org.university_admin_bff.exceptions.NotFoundException;
import reactor.core.publisher.Mono;

@Service
public class UserServiceClientImpl implements UserServiceClient {

    private WebClient userServiceWebClient;
    private WebClientErrorHandler webClientErrorHandler;

    @Autowired
    public UserServiceClientImpl(WebClient userServiceWebClient, WebClientErrorHandler webClientErrorHandler) {
        this.userServiceWebClient = userServiceWebClient;
        this.webClientErrorHandler = webClientErrorHandler;
    }

    @Override
    public LoginResponseDto login(String uri, String authHeader, LoginRequestDto loginRequestDto) {

        ClientResponse response = this.userServiceWebClient
                .post()
                .uri(uri)
                .header("Authorization", authHeader)
                .bodyValue(loginRequestDto)
                .exchange().block();

        webClientErrorHandler.handleHttpErrors(response);

        return response.bodyToMono(LoginResponseDto.class).block();


    }

    @Override
    public UpdateAdminDto getAdminUpdateDto(String uri, String authHeader) {

        ClientResponse response =  this.userServiceWebClient
                .get()
                .uri(uri)
                .header("Authorization", authHeader)
                .exchange().block();

        webClientErrorHandler.handleHttpErrors(response);

        return response.bodyToMono(UpdateAdminDto.class).block();

    }

    @Override
    public UpdateAdminDto updateAdmin(String uri, String authHeader, UpdateAdminDto updateAdminDto) {

        ClientResponse response = this.userServiceWebClient
                .put()
                .uri(uri)
                .header("Authorization", authHeader)
                .bodyValue(updateAdminDto)
                .exchange().block();

        webClientErrorHandler.handleHttpErrors(response);

        return  response.bodyToMono(UpdateAdminDto.class).block();

    }

    @Override
    public LoginResponseDto updateEmail(String uri, String authHeader, UpdateEmailDto updateEmailDto) {

        ClientResponse response = this.userServiceWebClient
                .put()
                .uri(uri)
                .header("Authorization", authHeader)
                .bodyValue(updateEmailDto)
                .exchange().block();

        webClientErrorHandler.handleHttpErrors(response);

        return response.bodyToMono(LoginResponseDto.class).block();

    }

    @Override
    public SingleMessageDto updatePassword(String uri, String authHeader, ChangePasswordDto changePasswordDto) {

        ClientResponse response = this.userServiceWebClient
                .post()
                .uri(uri)
                .header("Authorization", authHeader)
                .bodyValue(changePasswordDto)
                .exchange().block();

        webClientErrorHandler.handleHttpErrors(response);

        return response.bodyToMono(SingleMessageDto.class).block();

    }

    @Override
    public Void confirmPasswordChange(String uri, String authHeader) {

        ClientResponse response = this.userServiceWebClient
                .get()
                .uri(uri)
                .header("Authorization", authHeader)
                .exchange().block();

        webClientErrorHandler.handleHttpErrors(response);

        return response.bodyToMono(Void.class).block();

    }


}
