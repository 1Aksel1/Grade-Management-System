package org.university_student_client_app.dto;

import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class RegisterStudentDto {

    private String name;
    private String surname;
    private String username;
    private String password;
    private String email;
    private String indexNumber;
    private String telephoneNumber;
    private String dateOfBirth;

    public RegisterStudentDto(String name, String surname, String username, String password, String email, String indexNumber, String telephoneNumber, String dateOfBirth) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.indexNumber = indexNumber;
        this.telephoneNumber = telephoneNumber;
        this.dateOfBirth = dateOfBirth;
    }

    public RegisterStudentDto() {
    }



}
