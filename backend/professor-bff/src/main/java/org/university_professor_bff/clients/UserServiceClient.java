package org.university_professor_bff.clients;

import org.university_professor_bff.dtos.*;

import javax.servlet.http.HttpServletResponse;

public interface UserServiceClient {


    public LoginResponseDto login(String uri, LoginRequestDto loginRequestDto, String authHeader);

    public SingleMessageDto register(String uri, String authHeader, RegisterProfessorDto registerProfessorDto, HttpServletResponse httpServletResponse);

    public SingleMessageDto resendActivationEmail(String uri, String authHeader, String cookie);

    public Void activateRegistration(String uri, String authHeader);

    public UpdateProfessorDto getUpdateProfessor(String uri, String authHeader);

    public UpdateProfessorDto updateProfessor(String uri, String authHeader, UpdateProfessorDto updateProfessorDto);

    public LoginResponseDto updateEmail(String uri, String authHeader, UpdateEmailDto updateEmailDto);

    public SingleMessageDto updatePassword(String uri, String authHeader, ChangePasswordDto changePasswordDto);

    public Void confirmPasswordChange(String uri, String authHeader);


}
