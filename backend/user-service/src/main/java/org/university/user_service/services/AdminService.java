package org.university.user_service.services;

import org.university.user_service.dtos.UpdateAdminDto;
import org.university.user_service.dtos.UpdateEmailDto;

public interface AdminService {

    public UpdateAdminDto updateAdmin(String authHeader, UpdateAdminDto updateAdminDto);

    public UpdateAdminDto getAdminForUpdate(String authHeader);

    public void changeEmail(String authHeader, UpdateEmailDto updateEmailDto);

    public void checkPasswordMatch(String authHeader, String oldPassword);

    public void changePassword(String linkIdentifier);

}
