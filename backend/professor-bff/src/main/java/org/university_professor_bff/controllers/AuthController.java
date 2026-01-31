package org.university_professor_bff.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.university_professor_bff.dtos.LoginRequestDto;
import org.university_professor_bff.dtos.LoginResponseDto;
import org.university_professor_bff.dtos.RegisterProfessorDto;
import org.university_professor_bff.dtos.SingleMessageDto;
import org.university_professor_bff.services.UserService;
import org.university_professor_bff.utils.JwtUtil;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping
public class AuthController {

    private UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(userService.login(loginRequestDto));
    }


    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SingleMessageDto> registerProfessor(@Valid @RequestBody RegisterProfessorDto registerProfessorDto, HttpServletResponse httpServletResponse) {
        return ResponseEntity.ok(userService.register(registerProfessorDto, httpServletResponse));
    }

    @GetMapping(value = "/resendActivationEmail")
    public ResponseEntity<SingleMessageDto> resendActivationEmail(@RequestHeader("Cookie") String cookie) {
        return ResponseEntity.ok(userService.resendActivationEmail(cookie));
    }

    @GetMapping(path = "/activate")
    public ResponseEntity<Void> activateRegistration(@RequestParam("token") String activationLink) {

        userService.activateRegistration(activationLink);

        URI redirectUri = URI.create("http://localhost:4200/confirmRegistration");

        return ResponseEntity.status(HttpStatus.PERMANENT_REDIRECT)
                .location(redirectUri)
                .build();

    }




}
