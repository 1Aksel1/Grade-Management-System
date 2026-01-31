package org.university.user_service.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.university.user_service.dtos.*;
import org.university.user_service.exceptions.ChangePasswordException;
import org.university.user_service.exceptions.PasswordNotMatchedException;
import org.university.user_service.exceptions.UniqueConstraintViolationException;
import org.university.user_service.exceptions.UserNotFoundException;
import org.university.user_service.mappers.StudentMapper;
import org.university.user_service.model.*;
import org.university.user_service.repositories.AdminRepository;
import org.university.user_service.repositories.ProfessorRepository;
import org.university.user_service.repositories.RegisteredExamRepository;
import org.university.user_service.repositories.StudentRepository;
import org.university.user_service.services.ChangePasswordRequestService;
import org.university.user_service.services.RegistrationService;
import org.university.user_service.services.StudentService;
import org.university.user_service.utils.JwtUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final AdminRepository adminRepository;
    private final ProfessorRepository professorRepository;
    private final RegisteredExamRepository registeredExamRepository;
    private final RegistrationService registrationService;
    private final ChangePasswordRequestService passwordRequestService;
    private final StudentMapper studentMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, RegistrationService registrationService, @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") StudentMapper studentMapper, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, ChangePasswordRequestService passwordRequestService, RegisteredExamRepository registeredExamRepository, AdminRepository adminRepository, ProfessorRepository professorRepository) {
        this.studentRepository = studentRepository;
        this.registrationService = registrationService;
        this.studentMapper = studentMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.passwordRequestService = passwordRequestService;
        this.registeredExamRepository = registeredExamRepository;
        this.adminRepository = adminRepository;
        this.professorRepository = professorRepository;
    }



    @Transactional
    public void registerStudent(CreateStudentDto createStudentDto) {

        Student student = studentMapper.toEntityFromCreateDto(createStudentDto);
        student.setPassword(passwordEncoder.encode(createStudentDto.getPassword()));

        checkIfEmailIsUnique(createStudentDto.getEmail());
        checkIfUsernameAndTelephoneAreUnique(createStudentDto.getUsername(), createStudentDto.getTelephoneNumber());

        student = studentRepository.save(student);

        registrationService.createRegistration(student.getId(), student.getName(), student.getSurname(), UserType.STUDENT, student.getEmail());


    }

    @Transactional
    public UpdateStudentDto updateStudent(String authHeader, UpdateStudentDto updateStudentDto) {

        String jwt = authHeader.substring(7);
        Long id = jwtUtil.extractUserId(jwt);
        updateStudentDto.setEmail(jwtUtil.extractEmail(jwt));

        Optional<Student> optionalStudent = studentRepository.findById(id);

        if(!optionalStudent.isPresent()) {
            throw new UserNotFoundException();
        }

        checkIfUsernameAndTelephoneAreUnique(updateStudentDto.getUsername(), updateStudentDto.getTelephoneNumber());

        Student student = optionalStudent.get();
        studentMapper.updateStudentFromDto(updateStudentDto, student);

        return studentMapper.toUpdateDtoFromEntity(studentRepository.save(student));

    }


    @Transactional
    public void changeEmail(String authHeader, UpdateEmailDto updateEmailDto) {

        String jwt = authHeader.substring(7);
        Long id = jwtUtil.extractUserId(jwt);

        Optional<Student> studentOptional = studentRepository.findById(id);

        if(!studentOptional.isPresent()) {
            throw new UserNotFoundException();
        }

        checkIfEmailIsUnique(updateEmailDto.getEmail());

        Student student = studentOptional.get();
        student.setEmail(updateEmailDto.getEmail());
        studentRepository.save(student);

    }

    public UpdateStudentDto getStudentForUpdate(String authHeader) {

        String jwt = authHeader.substring(7);
        Long id = jwtUtil.extractUserId(jwt);

        Optional<Student> optionalStudent = studentRepository.findById(id);

        if(!optionalStudent.isPresent()) {
            throw new UserNotFoundException();
        }

        return studentMapper.toUpdateDtoFromEntity(optionalStudent.get());

    }

    @Override
    public Long getStudentIdForEmail(String email) {

        Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);

        if(!studentOptional.isPresent()) {
            throw new UserNotFoundException();
        }

        return studentOptional.get().getId();
    }

    @Override
    public void checkPasswordMatch(String authHeader, String oldPassword) {

        String encodedPassword = "";

        String jwt = authHeader.substring(7);
        Long userId = jwtUtil.extractUserId(jwt);

        Optional<Student> studentOptional = studentRepository.findById(userId);

        if(studentOptional.isPresent()) {
            encodedPassword = studentOptional.get().getPassword();
        }

        if(!passwordEncoder.matches(oldPassword, encodedPassword)) {
            throw new PasswordNotMatchedException();
        }


    }

    @Transactional
    public void changePassword(String linkIdentifier) {

        ChangePasswordRequest passwordRequest = passwordRequestService.executePasswordChange(linkIdentifier);

        UserType userType = passwordRequest.getUserType();

        if(!userType.equals(UserType.STUDENT)) {
            throw new ChangePasswordException("The link is not valid and has expired!");
        }

        Long userId = passwordRequest.getUserId();
        String newPassword = passwordRequest.getNewPassword();

        studentRepository.setNewPassword(userId, newPassword);


    }

    @Transactional
    public Boolean registerExam(RegisterExamDto registerExamDto) {

        Long studentId = registerExamDto.getStudentId();
        String courseName = registerExamDto.getCourseName();
        Period examPeriod = Period.valueOf(registerExamDto.getExamPeriod());

        Optional<Student> studentOptional = studentRepository.findById(studentId);

        if(!studentOptional.isPresent()){
            return false;
        }

        Student student = studentOptional.get();

        if(registeredExamRepository.existsByCourseNameAndStudentAndExamPeriod(courseName,student,examPeriod)){
            return false;
        }

        try {

            RegisteredExam registeredExam = new RegisteredExam(courseName, examPeriod, student);
            registeredExamRepository.save(registeredExam);

        } catch (DataIntegrityViolationException e) {
            return false;
        }


        return true;


    }


    @Transactional
    public Boolean isExamRegistered(String studentIndex, String courseName, String examPeriod) {

        Optional<Student> studentOptional = studentRepository.findStudentByIndexNumber(studentIndex);

        if(!studentOptional.isPresent()) {
            return false;
        }

        Student student = studentOptional.get();

        if(registeredExamRepository.deleteRegisteredExamIfExists(student, courseName, Period.valueOf(examPeriod)) == 0) {
            return false;
        }

        return true;

    }


    @Override
    public List<StudentUsernameAndEmailDto> findStudentUsernamesAndEmails() {
        return studentRepository.findStudentUsernamesAndEmails();
    }

    @Override
    public StudentNameAndEmailDto findStudentNameAndEmail(String studentIndex) {
        return studentRepository.findStudentNameAndEmail(studentIndex);
    }


    @Override
    public String getStudentEmail(String authHeader) {

        String jwt = authHeader.substring(7);
        Long userId = jwtUtil.extractUserId(jwt);

        Optional<Student> studentOptional = studentRepository.findById(userId);

        if(studentOptional.isPresent()) {
            return studentOptional.get().getEmail();
        }

        return "";

    }


    @Override
    public List<RegisterExamDto> findAllRegisteredExams(String authHeader) {

        List<RegisterExamDto> registerExamDtos = new ArrayList<>();

        String jwt = authHeader.substring(7);
        Long userId = jwtUtil.extractUserId(jwt);

        Optional<Student> studentOptional = studentRepository.findById(userId);

        if(!studentOptional.isPresent()) {
            throw new UserNotFoundException();
        }


        List<RegisteredExam> registeredExamsList = registeredExamRepository.findAllByStudent(studentOptional.get());

        registeredExamsList.forEach(registeredExam -> {
            registerExamDtos.add(new RegisterExamDto(registeredExam.getCourseName(), registeredExam.getExamPeriod().toString()));
        });

        return registerExamDtos;
    }



    private void checkIfUsernameAndTelephoneAreUnique(String username, String telephoneNumber) {

        Optional<Professor> professorOptional = professorRepository.findProfessorByUsernameOrTelephoneNumber(username, telephoneNumber);

        if(professorOptional.isPresent()) {
            throw new UniqueConstraintViolationException("Username and telephone number must be unique!");
        }

        Optional<Admin> adminOptional = adminRepository.findAdminByUsernameOrTelephoneNumber(username, telephoneNumber);

        if(adminOptional.isPresent()) {
            throw new UniqueConstraintViolationException("Username and telephone number must be unique!");
        }

    }

    private void checkIfEmailIsUnique(String email) {

        Optional<Professor> professorOptional = professorRepository.findProfessorByEmail(email);

        if(professorOptional.isPresent()) {
            throw new UniqueConstraintViolationException("Email must be unique!");
        }

        Optional<Admin> adminOptional = adminRepository.findAdminByEmail(email);

        if(adminOptional.isPresent()) {
            throw new UniqueConstraintViolationException("Email must be unique!");
        }

    }




}
