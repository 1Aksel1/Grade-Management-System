package org.university.user_service.dtos;

import lombok.Data;

@Data
public class LoginResponseDto {

    private String jwt;

    public LoginResponseDto(String jwt) {
        this.jwt = jwt;
    }


}
