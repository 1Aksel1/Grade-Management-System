package org.university_admin_bff.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class ExamPeriodStatusDto {

    @Pattern(regexp = "JANUARY|FEBRUARY|JUNE|JULY|AUGUST|SEPTEMBER", message = "Invalid exam period")
    @NotBlank(message = "Can't be blank")
    private String examPeriod;



}
