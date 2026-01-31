package org.university.user_service.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.university.user_service.dtos.ChangePasswordDto;
import org.university.user_service.exceptions.ChangePasswordException;
import org.university.user_service.model.ChangePasswordRequest;
import org.university.user_service.model.UserType;
import org.university.user_service.repositories.AdminRepository;
import org.university.user_service.repositories.ChangePasswordRequestRepository;
import org.university.user_service.repositories.ProfessorRepository;
import org.university.user_service.repositories.StudentRepository;
import org.university.user_service.services.ChangePasswordRequestService;
import org.university.user_service.services.SendNotificationsService;
import org.university.user_service.utils.JwtUtil;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class ChangePasswordRequestImpl implements ChangePasswordRequestService {


    private ChangePasswordRequestRepository changePasswordRepository;
    private SendNotificationsService sendNotificationsService;
    private JwtUtil jwtUtil;
    private PasswordEncoder passwordEncoder;
    private AdminRepository adminRepository;
    private StudentRepository studentRepository;
    private ProfessorRepository professorRepository;


    @Autowired
    public ChangePasswordRequestImpl(ChangePasswordRequestRepository changePasswordRepository, SendNotificationsService sendNotificationsService,  JwtUtil jwtUtil, PasswordEncoder passwordEncoder, AdminRepository adminRepository, StudentRepository studentRepository, ProfessorRepository professorRepository) {
        this.changePasswordRepository = changePasswordRepository;
        this.sendNotificationsService = sendNotificationsService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.adminRepository = adminRepository;
        this.professorRepository = professorRepository;
        this.studentRepository = studentRepository;
    }


    @Override
    public void changePassword(String linkIdentifier) {

    }

    @Override
    public ChangePasswordRequest executePasswordChange(String linkIdentifier) {

        Optional<ChangePasswordRequest> passwordRequestOptional = changePasswordRepository.findChangePasswordRequestByLinkIdentifier(linkIdentifier);

        if(!passwordRequestOptional.isPresent()) {
            throw new ChangePasswordException("The link is not valid and has expired!");
        }

        ChangePasswordRequest passwordRequest = passwordRequestOptional.get();
        changePasswordRepository.deleteById(passwordRequest.getId());


        return passwordRequest;
    }

    @Transactional
    public void changePassword(String linkIdentifier, UserType admin) {

        Optional<ChangePasswordRequest> passwordRequestOptional = changePasswordRepository.findChangePasswordRequestByLinkIdentifier(linkIdentifier);

        if(!passwordRequestOptional.isPresent()) {
            throw new ChangePasswordException("The link is not valid and has expired!");
        }

        ChangePasswordRequest passwordRequest = passwordRequestOptional.get();
        changePasswordRepository.deleteById(passwordRequest.getId());

        Long userId = passwordRequest.getUserId();
        UserType userType = passwordRequest.getUserType();
        String newPassword = passwordRequest.getNewPassword();

        if(userType.equals(UserType.ADMIN)) {
            adminRepository.setNewPassword(userId, newPassword);
        } else if(userType.equals(UserType.STUDENT)) {
            studentRepository.setNewPassword(userId, newPassword);
        } else if(userType.equals(UserType.PROFESSOR)) {
            professorRepository.setNewPassword(userId, newPassword);
        }

    }

    @Override
    public void deletePasswordRequest(Long id) {
        changePasswordRepository.deleteById(id);
    }

    @Override
    public void createPasswordChangeRequest(String authHeader, ChangePasswordDto changePasswordDto) {

        String jwt = authHeader.substring(7);
        Map<String, Object> claims = jwtUtil.extractAllClaims(jwt);

        String email = jwtUtil.extractEmail(jwt);
        String username = jwtUtil.extractUsername(jwt);
        Long userId = Long.valueOf((Integer) claims.get("id"));
        UserType userType = UserType.valueOf((String) claims.get("role"));


        Optional<ChangePasswordRequest> passwordRequestOptional = changePasswordRepository.findChangePasswordRequestByUserIdAndUserType(userId, userType);
        passwordRequestOptional.ifPresent(changePasswordRequest -> changePasswordRepository.delete(changePasswordRequest));


        UUID uuid = UUID.randomUUID();
        String linkIdentifier = uuid.toString();
        String newPasswordEncoded = passwordEncoder.encode(changePasswordDto.getNewPassword());


        ChangePasswordRequest passwordRequest = new ChangePasswordRequest(linkIdentifier, userId, userType, newPasswordEncoded, LocalDateTime.now());
        changePasswordRepository.save(passwordRequest);

        sendNotificationsService.sendChangePasswordNotification(email, username, userType, linkIdentifier);

    }


    @Scheduled(fixedDelay = 60000)
    @Transactional
    public void removeExpiredRequests() {
        changePasswordRepository.deleteExpiredRequests(LocalDateTime.now().minusMinutes(5));
    }

}
