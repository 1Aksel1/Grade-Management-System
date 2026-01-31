package org.university_admin_bff.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.university_admin_bff.dtos.LoginRequestDto;
import org.university_admin_bff.dtos.LoginResponseDto;
import org.university_admin_bff.services.UserService;
import org.university_admin_bff.utils.JwtUtil;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping( path = "/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto){
        return ResponseEntity.ok(userService.login(loginRequestDto));
    }




}
