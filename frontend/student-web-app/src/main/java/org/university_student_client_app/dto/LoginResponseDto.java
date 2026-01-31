package org.university_student_client_app.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginResponseDto implements Serializable {

    private String jwt;

    public LoginResponseDto(String jwt) {
        this.jwt = jwt;
    }

    public LoginResponseDto() {
    }


}
