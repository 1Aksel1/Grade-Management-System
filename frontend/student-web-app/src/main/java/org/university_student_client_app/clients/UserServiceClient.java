package org.university_student_client_app.clients;

import org.university_student_client_app.dto.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserServiceClient {

    public LoginResponseDto login(LoginRequestDto loginRequestDto);

    public SingleMessageDto register(RegisterStudentDto registerStudentDto, HttpServletResponse httpServletResponse);

    public UpdateStudentDto getUpdateStudentDto(String jwt);

    public UpdateStudentDto updateStudent(String jwt, UpdateStudentDto updateStudentDto);

    public LoginResponseDto updateEmail(String jwt, UpdateEmailDto updateEmailDto);

    public SingleMessageDto updatePassword(String jwt, ChangePasswordDto changePasswordDto);

    public List<RegisterExamDto> getRegisteredExams(String jwt);

}
