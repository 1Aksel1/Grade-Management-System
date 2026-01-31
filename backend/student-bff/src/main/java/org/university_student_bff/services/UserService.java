package org.university_student_bff.services;

import org.university_student_bff.dtos.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserService {

    public LoginResponseDto login(LoginRequestDto loginRequestDto);

    public SingleMessageDto register(RegisterStudentDto registerStudentDto, HttpServletResponse httpServletResponse);

    public SingleMessageDto resendActivationEmail(String cookie);

    public void activateRegistration(String activationLink);

    public UpdateStudentDto getUpdateStudent(String authHeader);

    public UpdateStudentDto updateStudent(String authHeader, UpdateStudentDto updateStudentDto);

    public LoginResponseDto updateEmail(String authHeader, UpdateEmailDto updateEmailDto);

    public SingleMessageDto updatePassword(String authHeader, ChangePasswordDto changePasswordDto);

    public void confirmPasswordChange(String activationLink);

    public List<RegisterExamDto> getRegisteredExams(String authHeader);


}
