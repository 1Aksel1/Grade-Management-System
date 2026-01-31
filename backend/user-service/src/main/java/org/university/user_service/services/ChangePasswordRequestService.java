package org.university.user_service.services;

import org.university.user_service.dtos.ChangePasswordDto;
import org.university.user_service.model.ChangePasswordRequest;
import org.university.user_service.model.UserType;

public interface ChangePasswordRequestService {

    public ChangePasswordRequest executePasswordChange(String linkIdentifier);

    public void changePassword(String linkIdentifier);

    public void createPasswordChangeRequest(String authHeader, ChangePasswordDto changePasswordDto);

    public void removeExpiredRequests();

    public void deletePasswordRequest(Long id);


}
