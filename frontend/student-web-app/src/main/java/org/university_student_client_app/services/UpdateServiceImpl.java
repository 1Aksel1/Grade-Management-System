package org.university_student_client_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.university_student_client_app.clients.UserServiceClient;
import org.university_student_client_app.dto.*;

@Service
public class UpdateServiceImpl implements UpdateService {

    private UserServiceClient userServiceClient;

    @Autowired
    public UpdateServiceImpl(UserServiceClient userServiceClient) {
        this.userServiceClient = userServiceClient;
    }

    @Override
    public UpdateStudentDto getUpdateStudentDto(String jwt) {
        return userServiceClient.getUpdateStudentDto(jwt);
    }

    @Override
    public UpdateStudentDto updateStudent(String jwt, UpdateStudentDto updateStudentDto) {
        return userServiceClient.updateStudent(jwt, updateStudentDto);
    }

    @Override
    public LoginResponseDto updateEmail(String jwt, UpdateEmailDto updateEmailDto) {
        return userServiceClient.updateEmail(jwt, updateEmailDto);
    }

    @Override
    public SingleMessageDto updatePassword(String jwt, ChangePasswordDto changePasswordDto) {
        return userServiceClient.updatePassword(jwt, changePasswordDto);
    }
}
