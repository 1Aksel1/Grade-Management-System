package org.university_grade_management_service.dtos;

import lombok.Data;
import org.university_grade_management_service.model.Period;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class ExamPeriodDto {


    @Pattern(regexp = "JANUARY|FEBRUARY|JUNE|JULY|AUGUST|SEPTEMBER", message = "Invalid exam period")
    @NotBlank(message = "Can't be blank")
    private String examPeriod;


}
