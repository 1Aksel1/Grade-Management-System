package com.example.admindesktopapp.services;

import com.example.admindesktopapp.clients.UserServiceClient;
import com.example.admindesktopapp.dto.*;
import com.example.admindesktopapp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service
public class UpdateServiceImpl implements UpdateService {


    private UserServiceClient userServiceClient;
    private JwtUtil jwtUtil;

    @Autowired
    public UpdateServiceImpl(UserServiceClient userServiceClient, JwtUtil jwtUtil) {
        this.userServiceClient = userServiceClient;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public UpdateAdminDto getUpdateAdminDto() {
        return userServiceClient.getAdminUpdateDto();
    }

    @Override
    public UpdateAdminDto updateAdmin(String name, String surname, String username, String email, String telephoneNumber, String dateOfBirth) {

        if(name.equals("") || surname.equals("") || username.equals("") || email.equals("") || telephoneNumber.equals("") || dateOfBirth.equals("")) {
            throw new RuntimeException("Input fields can't be empty!");
        }
        validateDate(dateOfBirth);

        return userServiceClient.updateAdmin(new UpdateAdminDto(name, surname, username, email, telephoneNumber, dateOfBirth));
    }

    @Override
    public void updateEmail(String email) {

        if(email.equals("")) {
            throw new RuntimeException("Email field can't be empty!");
        }

        LoginResponseDto loginResponseDto = userServiceClient.updateEmail(new UpdateEmailDto(email));
        jwtUtil.setJwt(loginResponseDto.getJwt());

    }

    @Override
    public SingleMessageDto updatePassword(String oldPassword, String newPassword, String newPasswordCheck) {

        if(oldPassword.equals("") || newPassword.equals("") || newPasswordCheck.equals("")) {
            throw new RuntimeException("Input fields can't be empty!");
        }

        if(!newPassword.equals(newPasswordCheck)) {
            throw new RuntimeException("The passwords you provided don't match!");
        }

        return userServiceClient.updatePassword(new ChangePasswordDto(oldPassword, newPassword));
    }


    @Override
    public SingleMessageDto confirmPasswordChange(String link) {

        if(!link.startsWith("http://localhost:8080/admin-bff/api/profile/confirmPasswordChange?token=")) {
            throw new RuntimeException("The link you entered is not valid!");
        }

        String[] linkSplit = link.split("api");
        String uri = linkSplit[1];

        return userServiceClient.confirmPasswordChange(uri);

    }

    private void validateDate(String dateStr) {

        try {

            LocalDate.parse(dateStr);

        } catch (DateTimeParseException e) {
            throw new RuntimeException("The pattern of the date must be: yyyy/MM/dd!");
        }


    }

}
