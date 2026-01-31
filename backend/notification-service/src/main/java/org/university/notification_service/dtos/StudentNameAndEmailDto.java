package org.university.notification_service.dtos;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class StudentNameAndEmailDto {

    @NotBlank(message = "Can't be blank")
    @Size(min = 2, max = 20, message = "Must be between 2 and 20 characters")
    private String name;

    @NotBlank(message = "Can't be blank")
    @Email(message = "Invalid email format")
    private String email;

    public StudentNameAndEmailDto(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public StudentNameAndEmailDto() {
    }


}
