package org.university.user_service.dtos;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UpdateEmailDto {

    @NotBlank(message = "Can't be blank")
    @Email(message = "Invalid email format")
    private String email;

}
