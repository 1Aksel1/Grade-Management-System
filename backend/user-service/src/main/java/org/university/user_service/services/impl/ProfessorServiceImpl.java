package org.university.user_service.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.university.user_service.dtos.CreateProfessorDto;
import org.university.user_service.dtos.UpdateEmailDto;
import org.university.user_service.dtos.UpdateProfessorDto;
import org.university.user_service.exceptions.*;
import org.university.user_service.mappers.ProfessorMapper;
import org.university.user_service.model.*;
import org.university.user_service.repositories.AdminRepository;
import org.university.user_service.repositories.ProfessorRepository;
import org.university.user_service.repositories.StudentRepository;
import org.university.user_service.services.ChangePasswordRequestService;
import org.university.user_service.services.ProfessorService;
import org.university.user_service.services.RegistrationService;
import org.university.user_service.utils.JwtUtil;

import java.util.Optional;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    private ProfessorRepository professorRepository;
    private AdminRepository adminRepository;
    private StudentRepository studentRepository;
    private RegistrationService registrationService;
    private ChangePasswordRequestService passwordRequestService;
    private ProfessorMapper professorMapper;
    private PasswordEncoder passwordEncoder;
    private JwtUtil jwtUtil;


    @Autowired
    public ProfessorServiceImpl(ProfessorRepository professorRepository, RegistrationService registrationService, @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") ProfessorMapper professorMapper, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, ChangePasswordRequestService passwordRequestService, AdminRepository adminRepository, StudentRepository studentRepository) {
        this.professorRepository = professorRepository;
        this.registrationService = registrationService;
        this.professorMapper = professorMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.passwordRequestService = passwordRequestService;
        this.adminRepository = adminRepository;
        this.studentRepository = studentRepository;
    }

    @Transactional
    public void registerProfessor(CreateProfessorDto createProfessorDto) {

        Professor professor = professorMapper.toEntityFromCreateDto(createProfessorDto);
        professor.setPassword(passwordEncoder.encode(createProfessorDto.getPassword()));

        checkIfEmailIsUnique(createProfessorDto.getEmail());
        checkIfUsernameAndTelephoneAreUnique(createProfessorDto.getUsername(), createProfessorDto.getTelephoneNumber());

        professor = professorRepository.save(professor);

        registrationService.createRegistration(professor.getId(), professor.getName(), professor.getSurname(), UserType.PROFESSOR, professor.getEmail());


    }

    @Transactional
    public UpdateProfessorDto updateProfessor(String authHeader, UpdateProfessorDto updateProfessorDto) {

        String jwt = authHeader.substring(7);
        Long id = jwtUtil.extractUserId(jwt);
        updateProfessorDto.setEmail(jwtUtil.extractEmail(jwt));

        Optional<Professor> optionalProfessor = professorRepository.findById(id);

        if(!optionalProfessor.isPresent()) {
            throw new UserNotFoundException();
        }

        checkIfUsernameAndTelephoneAreUnique(updateProfessorDto.getUsername(), updateProfessorDto.getTelephoneNumber());

        Professor professor = optionalProfessor.get();
        professorMapper.updateProfessorFromDto(updateProfessorDto, professor);

        return professorMapper.toUpdateDtoFromEntity(professorRepository.save(professor));

    }

    @Transactional
    public void changeEmail(String authHeader, UpdateEmailDto updateEmailDto) {

        String encodedPassword = "";

        String jwt = authHeader.substring(7);
        Long id = jwtUtil.extractUserId(jwt);

        Optional<Professor> optionalProfessor = professorRepository.findById(id);

        if(!optionalProfessor.isPresent()) {
            throw new UserNotFoundException();
        }

        checkIfEmailIsUnique(updateEmailDto.getEmail());

        Professor professor = optionalProfessor.get();
        professor.setEmail(updateEmailDto.getEmail());
        professorRepository.save(professor);

    }

    public UpdateProfessorDto getProfessorForUpdate(String authHeader) {

        String jwt = authHeader.substring(7);
        Long id = jwtUtil.extractUserId(jwt);

        Optional<Professor> optionalProfessor = professorRepository.findById(id);

        if(!optionalProfessor.isPresent()) {
            throw new UserNotFoundException();
        }

        return professorMapper.toUpdateDtoFromEntity(optionalProfessor.get());
    }

    @Override
    public Long getProfessorIdForEmail(String email) {

        Optional<Professor> professorOptional = professorRepository.findProfessorByEmail(email);

        if(!professorOptional.isPresent()) {
            throw new UserNotFoundException();
        }

        return professorOptional.get().getId();
    }

    @Override
    public void checkPasswordMatch(String authHeader, String oldPassword) {

        String encodedPassword = "";

        String jwt = authHeader.substring(7);
        Long userId = jwtUtil.extractUserId(jwt);

        Optional<Professor> professorOptional = professorRepository.findById(userId);

        if(professorOptional.isPresent()) {
            encodedPassword = professorOptional.get().getPassword();
        }

        if(!passwordEncoder.matches(oldPassword, encodedPassword)){
            throw new PasswordNotMatchedException();
        }

    }

    @Transactional
    public void changePassword(String linkIdentifier) {

        ChangePasswordRequest passwordRequest = passwordRequestService.executePasswordChange(linkIdentifier);

        UserType userType = passwordRequest.getUserType();

        if(!userType.equals(UserType.PROFESSOR)) {
            throw new ChangePasswordException("The link is not valid and has expired!");
        }

        Long userId = passwordRequest.getUserId();
        String newPassword = passwordRequest.getNewPassword();

        professorRepository.setNewPassword(userId, newPassword);


    }


    @Override
    public String getProfessorEmail(String authHeader) {

        String jwt = authHeader.substring(7);
        Long userId = jwtUtil.extractUserId(jwt);

        Optional<Professor> professorOptional = professorRepository.findById(userId);

        if(professorOptional.isPresent()) {
            return professorOptional.get().getEmail();
        }

        return "";

    }


    private void checkIfUsernameAndTelephoneAreUnique(String username, String telephoneNumber) {

        Optional<Student> studentOptional = studentRepository.findStudentByUsernameOrTelephoneNumber(username, telephoneNumber);

        if(studentOptional.isPresent()) {
            throw new UniqueConstraintViolationException("Username and telephone number must be unique!");
        }

        Optional<Admin> adminOptional = adminRepository.findAdminByUsernameOrTelephoneNumber(username, telephoneNumber);

        if(adminOptional.isPresent()) {
            throw new UniqueConstraintViolationException("Username and telephone number must be unique!");
        }

    }

    private void checkIfEmailIsUnique(String email) {

        Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);

        if(studentOptional.isPresent()) {
            throw new UniqueConstraintViolationException("Email must be unique!");
        }

        Optional<Admin> adminOptional = adminRepository.findAdminByEmail(email);

        if(adminOptional.isPresent()) {
            throw new UniqueConstraintViolationException("Email must be unique!");
        }

    }





}
