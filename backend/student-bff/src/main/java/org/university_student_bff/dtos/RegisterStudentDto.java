package org.university_student_bff.dtos;

import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class RegisterStudentDto {

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
    @Size(min = 5, max = 15, message = "Must be between 5 and 15 characters")
    private String password;

    @NotBlank(message = "Can't be blank")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Can't be blank")
    @Size(min = 5, message = "Must contain at least 3 characters")
    private String indexNumber;

    @NotBlank(message = "Can't be blank")
    @Pattern(regexp = "^\\+?[0-9]+$", message = "Must contain digits and optionally start with a '+' sign\"")
    private String telephoneNumber;

    @NotNull(message = "Must not be null")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

}
