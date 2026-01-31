package org.university_grade_management_service.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class RegisterExamRequestDto {

    @NotBlank(message = "Can't be blank")
    @Size(min = 2, max = 40, message = "Subject name must be valid")
    private String courseName;

    @Pattern(regexp = "JANUARY|FEBRUARY|JUNE|JULY|AUGUST|SEPTEMBER", message = "Invalid exam period")
    @NotBlank(message = "Can't be blank")
    private String examPeriod;



}
