package org.university_admin_bff.clients;

import org.university_admin_bff.dtos.*;

public interface UserServiceClient {

    public LoginResponseDto login(String uri, String authHeader, LoginRequestDto loginRequestDto);

    public UpdateAdminDto getAdminUpdateDto(String uri, String authHeader);

    public UpdateAdminDto updateAdmin(String uri, String authHeader, UpdateAdminDto updateAdminDto);

    public LoginResponseDto updateEmail(String uri, String authHeader, UpdateEmailDto updateEmailDto);

    public SingleMessageDto updatePassword(String uri, String authHeader, ChangePasswordDto changePasswordDto);

    public Void confirmPasswordChange(String uri, String authHeader);



}
