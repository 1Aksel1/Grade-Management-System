package org.university_student_client_app.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class LoginRequestDto implements Serializable {

    @NotBlank(message = "Can't be blank")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Can't be blank")
    @Size(min = 5, max = 15, message = "Must be between 5 and 15 characters")
    private String password;

    public LoginRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public LoginRequestDto() {
    }


}
