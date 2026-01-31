package com.example.admindesktopapp.services;

import com.example.admindesktopapp.clients.UserServiceClient;
import com.example.admindesktopapp.dto.LoginRequestDto;
import com.example.admindesktopapp.dto.LoginResponseDto;
import com.example.admindesktopapp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private UserServiceClient userServiceClient;
    private JwtUtil jwtUtil;

    @Autowired
    public AuthServiceImpl(UserServiceClient userServiceClient, JwtUtil jwtUtil) {
        this.userServiceClient = userServiceClient;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void login(String email, String password) {

        if(email.equals("") || password.equals("")) {
            throw new RuntimeException("Input fields can't be empty!");
        }

        LoginResponseDto loginResponseDto = userServiceClient.login(new LoginRequestDto(email, password));
        String jwt = loginResponseDto.getJwt();

        if(!jwtUtil.extractRole(jwt).equals("ADMIN")) {
            throw new RuntimeException();
        }

        jwtUtil.setJwt(jwt);

    }

    @Override
    public void logout() {
        jwtUtil.removeJwtAndAuthHeader();
    }
}
