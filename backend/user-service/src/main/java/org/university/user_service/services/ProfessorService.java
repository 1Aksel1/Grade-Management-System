package org.university.user_service.services;

import org.university.user_service.dtos.CreateProfessorDto;
import org.university.user_service.dtos.UpdateEmailDto;
import org.university.user_service.dtos.UpdateProfessorDto;

public interface ProfessorService {

    public void registerProfessor(CreateProfessorDto createProfessorDto);

    public UpdateProfessorDto updateProfessor(String authHeader, UpdateProfessorDto updateProfessorDto);

    public UpdateProfessorDto getProfessorForUpdate(String authHeader);

    public Long getProfessorIdForEmail(String email);

    public void checkPasswordMatch(String authHeader, String oldPassword);

    public void changePassword(String linkIdentifier);

    public void changeEmail(String authHeader, UpdateEmailDto updateEmailDto);

    public String getProfessorEmail(String authHeader);



}
