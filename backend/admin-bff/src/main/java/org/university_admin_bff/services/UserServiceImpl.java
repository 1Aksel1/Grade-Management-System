package org.university_admin_bff.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.university_admin_bff.clients.UserServiceClient;
import org.university_admin_bff.dtos.*;
import org.university_admin_bff.exceptions.UnauthorizedException;
import org.university_admin_bff.utils.JwtUtil;

@Service
public class UserServiceImpl implements UserService {

    private UserServiceClient userServiceClient;
    private JwtUtil jwtUtil;

    @Autowired
    public UserServiceImpl(UserServiceClient userServiceClient, JwtUtil jwtUtil) {
        this.userServiceClient = userServiceClient;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {

        String uri = "/login";

        LoginResponseDto loginResponseDto = userServiceClient.login(uri, jwtUtil.getAdminAuthHeader(), loginRequestDto);

        if(!jwtUtil.isRoleAdmin(loginResponseDto.getJwt())) {
            throw new UnauthorizedException("Login unsuccessful! Please enter valid credentials!");
        }

        return loginResponseDto;

    }

    @Override
    public UpdateAdminDto getAdminUpdateDto(String authHeader) {

        String uri = "/admin";
        return userServiceClient.getAdminUpdateDto(uri, authHeader);

    }

    @Override
    public UpdateAdminDto updateAdmin(String authHeader, UpdateAdminDto updateAdminDto) {

        String uri = "/admin";
        return userServiceClient.updateAdmin(uri, authHeader, updateAdminDto);

    }

    @Override
    public LoginResponseDto updateEmail(String authHeader, UpdateEmailDto updateEmailDto) {

        String uri = "/admin/changeEmail";
        return userServiceClient.updateEmail(uri, authHeader, updateEmailDto);

    }

    @Override
    public SingleMessageDto updatePassword(String authHeader, ChangePasswordDto changePasswordDto) {

        String uri = "/admin/changePassword";
        return userServiceClient.updatePassword(uri, authHeader, changePasswordDto);

    }

    @Override
    public void confirmPasswordChange(String authHeader, String activationLink) {

        String uri = "/confirmPasswordChange?token=" + activationLink;
        userServiceClient.confirmPasswordChange(uri, authHeader);

    }
}
