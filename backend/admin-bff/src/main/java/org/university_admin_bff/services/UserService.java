package org.university_admin_bff.services;

import org.university_admin_bff.dtos.*;

public interface UserService {

    public LoginResponseDto login(LoginRequestDto loginRequestDto);

    public UpdateAdminDto getAdminUpdateDto(String authHeader);

    public UpdateAdminDto updateAdmin(String authHeader, UpdateAdminDto updateAdminDto);

    public LoginResponseDto updateEmail(String authHeader, UpdateEmailDto updateEmailDto);

    public SingleMessageDto updatePassword(String authHeader, ChangePasswordDto changePasswordDto);

    public void confirmPasswordChange(String authHeader, String activationLink);

}
