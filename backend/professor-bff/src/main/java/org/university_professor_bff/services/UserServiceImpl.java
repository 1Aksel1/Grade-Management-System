package org.university_professor_bff.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.university_professor_bff.clients.UserServiceClient;
import org.university_professor_bff.dtos.*;
import org.university_professor_bff.exceptions.UnauthorizedException;
import org.university_professor_bff.utils.JwtUtil;

import javax.servlet.http.HttpServletResponse;

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

        LoginResponseDto loginResponseDto = userServiceClient.login(uri, loginRequestDto, jwtUtil.getAdminAuthHeader());

        if(!jwtUtil.isRoleProfessor(loginResponseDto.getJwt())) {
            throw new UnauthorizedException("Login unsuccessful! Please enter valid credentials!");
        }

        return loginResponseDto;

    }


    @Override
    public SingleMessageDto register(RegisterProfessorDto registerProfessorDto, HttpServletResponse httpServletResponse) {

        String uri = "/professor/register";
        return userServiceClient.register(uri, jwtUtil.getAdminAuthHeader(), registerProfessorDto, httpServletResponse);

    }

    @Override
    public SingleMessageDto resendActivationEmail(String cookie) {

        String uri = "/resendActivationEmail";
        return userServiceClient.resendActivationEmail(uri, jwtUtil.getAdminAuthHeader(), cookie);

    }

    @Override
    public void activateRegistration(String activationLink) {

        String uri = "/activate?token=" + activationLink;
        userServiceClient.activateRegistration(uri, jwtUtil.getAdminAuthHeader());

    }

    @Override
    public UpdateProfessorDto getUpdateProfessor(String authHeader) {

        String uri = "/professor";
        return userServiceClient.getUpdateProfessor(uri, authHeader);


    }


    @Override
    public UpdateProfessorDto updateProfessor(String authHeader, UpdateProfessorDto updateProfessorDto) {

        String uri = "/professor";
        return userServiceClient.updateProfessor(uri, authHeader, updateProfessorDto);

    }

    @Override
    public LoginResponseDto updateEmail(String authHeader, UpdateEmailDto updateEmailDto) {

        String uri = "/professor/changeEmail";
        return userServiceClient.updateEmail(uri, authHeader, updateEmailDto);

    }


    @Override
    public SingleMessageDto updatePassword(String authHeader, ChangePasswordDto changePasswordDto) {

        String uri = "/professor/changePassword";
        return userServiceClient.updatePassword(uri, authHeader, changePasswordDto);

    }


    @Override
    public void confirmPasswordChange(String activationLink) {

        String uri = "/confirmPasswordChange?token=" + activationLink;
        userServiceClient.confirmPasswordChange(uri, jwtUtil.getAdminAuthHeader());

    }
}
