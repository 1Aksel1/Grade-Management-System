package org.university_student_client_app.services;

import org.university_student_client_app.dto.LoginRequestDto;
import org.university_student_client_app.dto.LoginResponseDto;
import org.university_student_client_app.dto.RegisterStudentDto;
import org.university_student_client_app.dto.SingleMessageDto;

import javax.servlet.http.HttpServletResponse;

public interface AuthService {

    public LoginResponseDto login(LoginRequestDto loginRequestDto);

    public SingleMessageDto register(RegisterStudentDto registerStudentDto, HttpServletResponse httpServletResponse);

}
