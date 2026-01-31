package org.university.user_service.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.university.user_service.dtos.UpdateAdminDto;
import org.university.user_service.dtos.UpdateEmailDto;
import org.university.user_service.exceptions.ChangePasswordException;
import org.university.user_service.exceptions.PasswordNotMatchedException;
import org.university.user_service.exceptions.UniqueConstraintViolationException;
import org.university.user_service.exceptions.UserNotFoundException;
import org.university.user_service.mappers.AdminMapper;
import org.university.user_service.model.*;
import org.university.user_service.repositories.AdminRepository;
import org.university.user_service.repositories.ProfessorRepository;
import org.university.user_service.repositories.StudentRepository;
import org.university.user_service.services.AdminService;
import org.university.user_service.services.ChangePasswordRequestService;
import org.university.user_service.utils.JwtUtil;

import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    private AdminRepository adminRepository;
    private StudentRepository studentRepository;
    private ProfessorRepository professorRepository;
    private ChangePasswordRequestService passwordRequestService;
    private AdminMapper adminMapper;
    private JwtUtil jwtUtil;
    private PasswordEncoder passwordEncoder;


    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository, @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") AdminMapper adminMapper, JwtUtil jwtUtil, PasswordEncoder passwordEncoder, ChangePasswordRequestService passwordRequestService, StudentRepository studentRepository, ProfessorRepository professorRepository) {
        this.adminRepository = adminRepository;
        this.adminMapper = adminMapper;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.passwordRequestService = passwordRequestService;
        this.professorRepository = professorRepository;
        this.studentRepository = studentRepository;
    }


    @Transactional
    public UpdateAdminDto updateAdmin(String authHeader, UpdateAdminDto updateAdminDto) {

        String jwt = authHeader.substring(7);
        Long id = jwtUtil.extractUserId(jwt);
        updateAdminDto.setEmail(jwtUtil.extractEmail(jwt));

        Optional<Admin> optionalAdmin = adminRepository.findById(id);

        if(!optionalAdmin.isPresent()){
            throw new UserNotFoundException();
        }

        Optional<Student> studentOptional = studentRepository.findStudentByUsernameOrTelephoneNumber(updateAdminDto.getUsername(), updateAdminDto.getTelephoneNumber());

        if(studentOptional.isPresent()) {
            throw new UniqueConstraintViolationException("Username and telephone number must be unique!");
        }

        Optional<Professor> professorOptional = professorRepository.findProfessorByUsernameOrTelephoneNumber(updateAdminDto.getUsername(), updateAdminDto.getTelephoneNumber());

        if(professorOptional.isPresent()) {
            throw new UniqueConstraintViolationException("Username and telephone number must be unique!");
        }


        Admin admin = optionalAdmin.get();
        adminMapper.updateAdminFromDto(updateAdminDto, admin);

        return adminMapper.toUpdateDtoFromEntity(adminRepository.save(admin));

    }

    public UpdateAdminDto getAdminForUpdate(String authHeader) {

        String jwt = authHeader.substring(7);
        Long id = jwtUtil.extractUserId(jwt);

        Optional<Admin> optionalAdmin = adminRepository.findById(id);

        if(!optionalAdmin.isPresent()){
            throw new UserNotFoundException();
        }

        return adminMapper.toUpdateDtoFromEntity(optionalAdmin.get());

    }

    @Override
    public void changeEmail(String authHeader, UpdateEmailDto updateEmailDto) {

        String jwt = authHeader.substring(7);
        Long userId = jwtUtil.extractUserId(jwt);

        Optional<Admin> adminOptional = adminRepository.findById(userId);

        if(!adminOptional.isPresent()) {
            throw new UserNotFoundException();
        }

        Optional<Student> studentOptional = studentRepository.findStudentByEmail(updateEmailDto.getEmail());

        if(studentOptional.isPresent()) {
            throw new UniqueConstraintViolationException("Email must be unique!");
        }

        Optional<Professor> professorOptional = professorRepository.findProfessorByEmail(updateEmailDto.getEmail());

        if(professorOptional.isPresent()) {
            throw new UniqueConstraintViolationException("Email must be unique!");
        }


        Admin admin = adminOptional.get();
        admin.setEmail(updateEmailDto.getEmail());
        adminRepository.save(admin);

    }

    public void checkPasswordMatch(String authHeader, String oldPassword) {

        String encodedPassword = "";

        String jwt = authHeader.substring(7);
        Long userId = jwtUtil.extractUserId(jwt);

        Optional<Admin> adminOptional = adminRepository.findById(userId);

        if(adminOptional.isPresent()){
            encodedPassword = adminOptional.get().getPassword();
        }

        if(!passwordEncoder.matches(oldPassword, encodedPassword)){
            throw new PasswordNotMatchedException();
        }


    }

    @Transactional
    public void changePassword(String linkIdentifier) {

        ChangePasswordRequest passwordRequest = passwordRequestService.executePasswordChange(linkIdentifier);

        UserType userType = passwordRequest.getUserType();

        if(!userType.equals(UserType.ADMIN)) {
            throw new ChangePasswordException("The link is not valid and has expired!");
        }


        Long userId = passwordRequest.getUserId();
        String newPassword = passwordRequest.getNewPassword();

        adminRepository.setNewPassword(userId, newPassword);


    }


}
