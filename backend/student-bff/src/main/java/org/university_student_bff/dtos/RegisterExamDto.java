package org.university_student_bff.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class RegisterExamDto {

    @NotBlank(message = "Can't be blank")
    @Size(min = 2, max = 35, message = "Subject name must be valid")
    private String courseName;

    @Pattern(regexp = "JANUARY|FEBRUARY|JUNE|JULY|AUGUST|SEPTEMBER", message = "Invalid exam period")
    @NotBlank(message = "Can't be blank")
    private String examPeriod;

    public RegisterExamDto(String courseName, String examPeriod) {
        this.courseName = courseName;
        this.examPeriod = examPeriod;
    }

    public RegisterExamDto() {
    }

}
