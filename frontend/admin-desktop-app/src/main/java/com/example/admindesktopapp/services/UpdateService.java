package com.example.admindesktopapp.services;

import com.example.admindesktopapp.dto.SingleMessageDto;
import com.example.admindesktopapp.dto.UpdateAdminDto;

public interface UpdateService {

    public UpdateAdminDto getUpdateAdminDto();

    public UpdateAdminDto updateAdmin(String name, String surname, String username, String email, String telephoneNumber, String dateOfBirth);

    public void updateEmail(String email);

    public SingleMessageDto updatePassword(String oldPassword, String newPassword, String newPasswordCheck);

    public SingleMessageDto confirmPasswordChange(String link);

}
