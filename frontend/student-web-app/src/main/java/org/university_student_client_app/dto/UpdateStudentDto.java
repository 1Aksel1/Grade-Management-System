package org.university_student_client_app.dto;

import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class UpdateStudentDto {

    private String name;
    private String surname;
    private String username;
    private String email;
    private String indexNumber;
    private String telephoneNumber;
    private String dateOfBirth;

    public UpdateStudentDto(String name, String surname, String username, String email, String indexNumber, String telephoneNumber, String dateOfBirth) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.indexNumber = indexNumber;
        this.telephoneNumber = telephoneNumber;
        this.dateOfBirth = dateOfBirth;
    }

    public UpdateStudentDto() {
    }


}
