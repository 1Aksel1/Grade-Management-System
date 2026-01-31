package org.university.user_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.university.user_service.dtos.*;
import org.university.user_service.model.UserType;
import org.university.user_service.services.ChangePasswordRequestService;
import org.university.user_service.services.ProfessorService;
import org.university.user_service.services.RegistrationService;
import org.university.user_service.utils.JwtUtil;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    private ProfessorService professorService;
    private RegistrationService registrationService;
    private ChangePasswordRequestService changePasswordService;
    private JwtUtil jwtUtil;

    @Autowired
    public ProfessorController(ProfessorService professorService, RegistrationService registrationService, ChangePasswordRequestService changePasswordService, JwtUtil jwtUtil) {
        this.professorService = professorService;
        this.registrationService = registrationService;
        this.changePasswordService = changePasswordService;
        this.jwtUtil = jwtUtil;
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SingleMessageDto> registerProfessor(@Valid @RequestBody CreateProfessorDto createProfessorDto) {


        professorService.registerProfessor(createProfessorDto);

        Long userId = professorService.getProfessorIdForEmail(createProfessorDto.getEmail());
        UserType userType = UserType.PROFESSOR;
        String sessionId = registrationService.getSessionIdForUserIdAndUserType(userId,userType);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.SET_COOKIE, "JSESSIONID=" + sessionId + "; HttpOnly; Path=/; Max-Age=3600");

        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).body(new SingleMessageDto("Successfully registered! Please check your email to finish the process!"));

    }


    @PreAuthorize("hasAuthority('PROFESSOR')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UpdateProfessorDto> getProfessorForUpdate(@RequestHeader("Authorization") String authHeader) {

        return ResponseEntity.ok(professorService.getProfessorForUpdate(authHeader));

    }

    @PreAuthorize("hasAuthority('PROFESSOR')")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UpdateProfessorDto> updateProfessor(@RequestHeader("Authorization") String authHeader, @Valid @RequestBody UpdateProfessorDto updateProfessorDto) {

        return ResponseEntity.ok(professorService.updateProfessor(authHeader, updateProfessorDto));

    }

    @PreAuthorize("hasAuthority('PROFESSOR')")
    @PutMapping(path = "/changeEmail", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponseDto> updateProfessorEmail(@RequestHeader("Authorization") String authHeader, @Valid @RequestBody UpdateEmailDto updateEmailDto) {

        professorService.changeEmail(authHeader, updateEmailDto);
        return ResponseEntity.ok(new LoginResponseDto(jwtUtil.generateToken(updateEmailDto.getEmail())));

    }


    @PreAuthorize("hasAuthority('PROFESSOR')")
    @PostMapping(path = "/changePassword", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SingleMessageDto> changePasswordRequest(@RequestHeader("Authorization") String authHeader, @Valid @RequestBody ChangePasswordDto changePasswordDto) {

        professorService.checkPasswordMatch(authHeader, changePasswordDto.getOldPassword());
        changePasswordService.createPasswordChangeRequest(authHeader, changePasswordDto);
        return ResponseEntity.ok(new SingleMessageDto("Please check your email to confirm the process of changing the password!"));

    }


    @PreAuthorize("hasAuthority('PROFESSOR')")
    @GetMapping(path = "/email")
    public ResponseEntity<String> getProfessorEmail(@RequestHeader("Authorization") String authHeader) {
        return ResponseEntity.ok(professorService.getProfessorEmail(authHeader));
    }





}

