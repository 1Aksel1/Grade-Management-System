package org.university_student_bff.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.university_student_bff.clients.UserServiceClient;
import org.university_student_bff.dtos.*;
import org.university_student_bff.exceptions.UnauthorizedException;
import org.university_student_bff.utils.JwtUtil;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

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

        if(!jwtUtil.isRoleStudent(loginResponseDto.getJwt())) {
            throw new UnauthorizedException("Login unsuccessful! Please enter valid credentials!");
        }

        return loginResponseDto;

    }


    @Override
    public SingleMessageDto register(RegisterStudentDto registerStudentDto, HttpServletResponse httpServletResponse) {

        String uri = "/student/register";
        return userServiceClient.register(uri, registerStudentDto, httpServletResponse, jwtUtil.getAdminAuthHeader());

    }

    @Override
    public SingleMessageDto resendActivationEmail(String cookie) {

        String uri = "/resendActivationEmail";
        return userServiceClient.resendActivationEmail(uri, cookie, jwtUtil.getAdminAuthHeader());

    }

    @Override
    public void activateRegistration(String activationLink) {

        String uri = "/activate?token=" + activationLink;
        userServiceClient.activateRegistration(uri, jwtUtil.getAdminAuthHeader());

    }

    @Override
    public UpdateStudentDto getUpdateStudent(String authHeader) {

        String uri = "/student";
        return userServiceClient.getUpdateStudent(uri, authHeader);

    }

    @Override
    public UpdateStudentDto updateStudent(String authHeader, UpdateStudentDto updateStudentDto) {

        String uri = "/student";
        return userServiceClient.updateStudent(uri, authHeader, updateStudentDto);

    }

    @Override
    public LoginResponseDto updateEmail(String authHeader, UpdateEmailDto updateEmailDto) {

        String uri = "/student/changeEmail";
        return userServiceClient.updateEmail(uri, authHeader, updateEmailDto);

    }

    @Override
    public SingleMessageDto updatePassword(String authHeader, ChangePasswordDto changePasswordDto) {

        String uri = "/student/changePassword";
        return userServiceClient.updatePassword(uri, authHeader, changePasswordDto);

    }

    @Override
    public void confirmPasswordChange(String activationLink) {

        String uri = "/confirmPasswordChange?token=" + activationLink;
        userServiceClient.confirmPasswordChange(uri, jwtUtil.getAdminAuthHeader());

    }

    @Override
    public List<RegisterExamDto> getRegisteredExams(String authHeader) {

        String uri = "/student/getAllRegisteredExams";
        return userServiceClient.getRegisteredExams(uri, authHeader);

    }

}
