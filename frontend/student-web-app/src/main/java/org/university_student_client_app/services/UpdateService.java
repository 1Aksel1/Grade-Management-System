package org.university_student_client_app.services;

import org.university_student_client_app.dto.*;

public interface UpdateService {

    public UpdateStudentDto getUpdateStudentDto(String jwt);

    public UpdateStudentDto updateStudent(String jwt, UpdateStudentDto updateStudentDto);

    public LoginResponseDto updateEmail(String jwt, UpdateEmailDto updateEmailDto);

    public SingleMessageDto updatePassword(String jwt, ChangePasswordDto changePasswordDto);

}
