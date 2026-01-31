package org.university_student_bff.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.university_student_bff.dtos.LoginRequestDto;
import org.university_student_bff.dtos.LoginResponseDto;
import org.university_student_bff.dtos.RegisterStudentDto;
import org.university_student_bff.dtos.SingleMessageDto;
import org.university_student_bff.services.UserService;
import org.university_student_bff.utils.JwtUtil;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;

@RestController
@CrossOrigin(origins = "http://localhost:8085", allowCredentials = "true")
@RequestMapping
public class AuthController {

    private UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping( path = "/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto){
        return ResponseEntity.ok(userService.login(loginRequestDto));
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SingleMessageDto> registerStudent(@Valid @RequestBody RegisterStudentDto registerStudentDto, HttpServletResponse httpServletResponse) {
        return ResponseEntity.ok(userService.register(registerStudentDto, httpServletResponse));
    }

    @GetMapping(value = "/resendActivationEmail")
    public ResponseEntity<SingleMessageDto> resendActivationEmail(@RequestHeader("Cookie") String cookie) {
        return ResponseEntity.ok(userService.resendActivationEmail(cookie));
    }

    @GetMapping(path = "/activate")
    public ResponseEntity<Void> activateRegistration(@RequestParam("token") String activationLink) {

        userService.activateRegistration(activationLink);

        URI redirectUri = URI.create("http://localhost:8085/confirmRegistration");

        return ResponseEntity.status(HttpStatus.PERMANENT_REDIRECT)
                .location(redirectUri)
                .build();

    }





}
