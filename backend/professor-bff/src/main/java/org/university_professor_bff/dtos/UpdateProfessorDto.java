package org.university_professor_bff.dtos;

import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class UpdateProfessorDto {

    @NotBlank(message = "Can't be blank")
    @Size(min = 2, max = 20, message = "Must be between 2 and 20 characters")
    private String name;

    @NotBlank(message = "Can't be blank")
    @Size(min = 2, max = 20, message = "Must be between 2 and 20 characters")
    private String surname;

    @NotBlank(message = "Can't be blank")
    @Size(min = 4, max = 15, message = "Must be between 4 and 15 characters")
    private String username;

    @NotBlank(message = "Can't be blank")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Can't be blank")
    @Pattern(regexp = "^\\+?[0-9]+$", message = "Telephone number can only contain digits and optionally start with a '+' sign\"")
    private String telephoneNumber;

    @NotNull(message = "Can't be null")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @NotNull(message = "Can't be null")
    @Past(message = "Date of hire must be in the past")
    private LocalDate hireDate;

    @NotBlank(message = "Can't be blank")
    @Size(min = 3, message = "Must contain at least 3 characters")
    private String subjects;




}
