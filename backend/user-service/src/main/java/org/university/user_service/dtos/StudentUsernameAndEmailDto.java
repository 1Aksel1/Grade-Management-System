package org.university.user_service.dtos;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class StudentUsernameAndEmailDto {

    @NotBlank(message = "Can't be blank")
    @Size(min = 4, max = 15, message = "Must be between 4 and 15 characters")
    private String username;

    @NotBlank(message = "Can't be blank")
    @Email(message = "Invalid email format")
    private String email;

    public StudentUsernameAndEmailDto(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public StudentUsernameAndEmailDto() {
    }

}
