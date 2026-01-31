package org.university_student_bff.clients;

import org.university_student_bff.dtos.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserServiceClient {


    public LoginResponseDto login(String uri, LoginRequestDto loginRequestDto, String authHeader);

    public SingleMessageDto register(String uri, RegisterStudentDto registerStudentDto, HttpServletResponse httpServletResponse, String authHeader);

    public SingleMessageDto resendActivationEmail(String uri, String cookie, String authHeader);

    public Void activateRegistration(String uri, String authHeader);

    public UpdateStudentDto getUpdateStudent(String uri, String authHeader);

    public UpdateStudentDto updateStudent(String uri, String authHeader, UpdateStudentDto updateStudentDto);

    public LoginResponseDto updateEmail(String uri, String authHeader, UpdateEmailDto updateEmailDto);

    public SingleMessageDto updatePassword(String uri, String authHeader, ChangePasswordDto changePasswordDto);

    public Void confirmPasswordChange(String uri, String authHeader);

    public List<RegisterExamDto> getRegisteredExams(String uri, String authHeader);




}
