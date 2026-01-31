package com.example.admindesktopapp.dto;

public class LoginResponseDto {

    private String jwt;

    public LoginResponseDto(String jwt) {
        this.jwt = jwt;
    }

    public LoginResponseDto() {
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

}
