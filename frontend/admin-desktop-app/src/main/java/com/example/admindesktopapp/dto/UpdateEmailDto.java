package com.example.admindesktopapp.dto;

public class UpdateEmailDto {

    private String email;

    public UpdateEmailDto(String email) {
        this.email = email;
    }

    public UpdateEmailDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
