package org.university_admin_bff.dtos;

import lombok.Data;

@Data
public class LoginResponseDto {

    private String jwt;

    public LoginResponseDto(String jwt) {
        this.jwt = jwt;
    }

    public LoginResponseDto() {
    }

}
