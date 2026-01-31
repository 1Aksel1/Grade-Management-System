package org.university_admin_bff.dtos;

import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class UpdateAdminDto {

    @NotBlank
    @Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters")
    private String name;

    @NotBlank
    @Size(min = 2, max = 20, message = "Surname must be between 2 and 20 characters")
    private String surname;

    @NotBlank
    @Size(min = 4, max = 15, message = "Username must be between 4 and 15 characters")
    private String username;

    @NotBlank
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank
    @Pattern(regexp = "^\\+?[0-9]+$", message = "Telephone number can only contain digits and optionally start with a '+' sign\"")
    private String telephoneNumber;

    @NotNull(message = "Date of birth can't be null")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;


}
