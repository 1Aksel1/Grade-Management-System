package org.university_student_client_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.university_student_client_app.clients.UserServiceClient;
import org.university_student_client_app.dto.LoginRequestDto;
import org.university_student_client_app.dto.LoginResponseDto;
import org.university_student_client_app.dto.RegisterStudentDto;
import org.university_student_client_app.dto.SingleMessageDto;

import javax.servlet.http.HttpServletResponse;

@Service
public class AuthServiceImpl implements AuthService {

    private UserServiceClient userServiceClient;

    @Autowired
    public AuthServiceImpl(UserServiceClient userServiceClient) {
        this.userServiceClient = userServiceClient;
    }

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        return userServiceClient.login(loginRequestDto);
    }

    @Override
    public SingleMessageDto register(RegisterStudentDto registerStudentDto, HttpServletResponse httpServletResponse) {
        return userServiceClient.register(registerStudentDto, httpServletResponse);
    }

}
