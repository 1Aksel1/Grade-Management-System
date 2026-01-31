package org.university_professor_bff.services;

import org.university_professor_bff.dtos.*;

import javax.servlet.http.HttpServletResponse;

public interface UserService {

    public LoginResponseDto login(LoginRequestDto loginRequestDto);

    public SingleMessageDto register(RegisterProfessorDto registerProfessorDto, HttpServletResponse httpServletResponse);

    public SingleMessageDto resendActivationEmail(String cookie);

    public void activateRegistration(String activationLink);

    public UpdateProfessorDto getUpdateProfessor(String authHeader);

    public UpdateProfessorDto updateProfessor(String authHeader, UpdateProfessorDto updateProfessorDto);

    public LoginResponseDto updateEmail(String authHeader, UpdateEmailDto updateEmailDto);

    public SingleMessageDto updatePassword(String authHeader, ChangePasswordDto changePasswordDto);

    public void confirmPasswordChange(String activationLink);



}
