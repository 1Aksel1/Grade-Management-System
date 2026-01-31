package org.university_professor_bff.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ChangePasswordDto {


    @NotBlank(message = "Can't be blank")
    @Size(min = 5, max = 15, message = "Must be between 5 and 15 characters")
    private String oldPassword;

    @NotBlank(message = "Can't be blank")
    @Size(min = 5, max = 15, message = "Must be between 5 and 15 characters")
    private String newPassword;


}
