package org.university.user_service.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.university.user_service.dtos.LoginRequestDto;
import org.university.user_service.dtos.LoginResponseDto;
import org.university.user_service.dtos.SingleMessageDto;
import org.university.user_service.model.UserType;
import org.university.user_service.services.ChangePasswordRequestService;
import org.university.user_service.services.RegistrationService;
import org.university.user_service.utils.JwtUtil;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping()
public class AuthController {

    private AuthenticationManager authenticationManager;
    private RegistrationService registrationService;
    private ChangePasswordRequestService changePasswordRequestService;
    private JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, RegistrationService registrationService, ChangePasswordRequestService changePasswordRequestService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.registrationService = registrationService;
        this.changePasswordRequestService = changePasswordRequestService;
        this.jwtUtil = jwtUtil;
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/resendActivationEmail")
    public ResponseEntity<SingleMessageDto> resendActivationEmail(@RequestHeader("Cookie") String cookie) {

        registrationService.resendRegistrationNotification(cookie);

        return ResponseEntity.ok(new SingleMessageDto("Activation email has been sent again! Please check your email to finish the process!"));

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(path = "/activate")
    public ResponseEntity<Void> activate(@RequestParam("token") String activationLink) {

        registrationService.activateUser(activationLink);
        return ResponseEntity.ok().build();

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(path = "/confirmPasswordChange", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> confirmPasswordChange(@RequestParam("token") String activationLink) {

        changePasswordRequestService.changePassword(activationLink);
        return ResponseEntity.ok().build();

    }


    @PostMapping( path = "/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword()));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(401).build();
        }

        return ResponseEntity.ok(new LoginResponseDto(jwtUtil.generateToken(loginRequestDto.getEmail())));
    }



}
