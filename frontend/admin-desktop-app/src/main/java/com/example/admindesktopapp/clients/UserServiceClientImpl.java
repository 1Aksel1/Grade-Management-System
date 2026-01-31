package com.example.admindesktopapp.clients;

import com.example.admindesktopapp.dto.*;
import com.example.admindesktopapp.util.ErrorHandler;
import com.example.admindesktopapp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class UserServiceClientImpl implements  UserServiceClient {

    private WebClient adminBffServiceClient;
    private JwtUtil jwtUtil;
    private ErrorHandler errorHandler;

    @Autowired
    public UserServiceClientImpl(WebClient adminBffServiceClient, JwtUtil jwtUtil, ErrorHandler errorHandler) {
        this.adminBffServiceClient = adminBffServiceClient;
        this.jwtUtil = jwtUtil;
        this.errorHandler = errorHandler;
    }

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {

        String uri = "/login";

        ClientResponse response = this.adminBffServiceClient
                .post()
                .uri(uri)
                .bodyValue(loginRequestDto)
                .exchange().block();

        errorHandler.handleHttpErrors(response);

        return response.bodyToMono(LoginResponseDto.class).block();

    }

    @Override
    public UpdateAdminDto getAdminUpdateDto() {

        String uri = "/profile";
        String authHeader = jwtUtil.getAuthHeader();

        ClientResponse response = this.adminBffServiceClient
                .get()
                .uri(uri)
                .header("Authorization", authHeader)
                .header("Content-Type", "application/json")
                .exchange()
                .block();

        errorHandler.handleHttpErrors(response);

        return response.bodyToMono(UpdateAdminDto.class).block();

    }

    @Override
    public UpdateAdminDto updateAdmin(UpdateAdminDto updateAdminDto) {

        String uri = "/profile";
        String authHeader = jwtUtil.getAuthHeader();

        ClientResponse response = this.adminBffServiceClient
                .put()
                .uri(uri)
                .header("Authorization", authHeader)
                .header("Content-Type", "application/json")
                .bodyValue(updateAdminDto)
                .exchange()
                .block();

        errorHandler.handleHttpErrors(response);

        return response.bodyToMono(UpdateAdminDto.class).block();
    }

    @Override
    public LoginResponseDto updateEmail(UpdateEmailDto updateEmailDto) {

        String uri = "/profile/changeEmail";
        String authHeader = jwtUtil.getAuthHeader();

        ClientResponse response = this.adminBffServiceClient
                .put()
                .uri(uri)
                .header("Authorization", authHeader)
                .header("Content-Type", "application/json")
                .bodyValue(updateEmailDto)
                .exchange()
                .block();

        errorHandler.handleHttpErrors(response);

        return response.bodyToMono(LoginResponseDto.class).block();

    }

    @Override
    public SingleMessageDto updatePassword(ChangePasswordDto changePasswordDto) {

        String uri = "/profile/changePassword";
        String authHeader = jwtUtil.getAuthHeader();

        ClientResponse response = this.adminBffServiceClient
                .post()
                .uri(uri)
                .header("Authorization", authHeader)
                .header("Content-Type", "application/json")
                .bodyValue(changePasswordDto)
                .exchange()
                .block();

        errorHandler.handleHttpErrors(response);

        return response.bodyToMono(SingleMessageDto.class).block();

    }


    @Override
    public SingleMessageDto confirmPasswordChange(String uri) {

        String authHeader = jwtUtil.getAuthHeader();

        ClientResponse response = this.adminBffServiceClient
                .get()
                .uri(uri)
                .header("Authorization", authHeader)
                .exchange()
                .block();

        errorHandler.handleHttpErrors(response);

        return response.bodyToMono(SingleMessageDto.class).block();

    }
}
