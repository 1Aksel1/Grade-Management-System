package com.example.admindesktopapp.clients;

import com.example.admindesktopapp.dto.*;

public interface UserServiceClient {

    public LoginResponseDto login(LoginRequestDto loginRequestDto);

    public UpdateAdminDto updateAdmin(UpdateAdminDto updateAdminDto);

    public UpdateAdminDto getAdminUpdateDto();

    public LoginResponseDto updateEmail(UpdateEmailDto updateEmailDto);

    public SingleMessageDto updatePassword(ChangePasswordDto changePasswordDto);

    public SingleMessageDto confirmPasswordChange(String uri);

}
