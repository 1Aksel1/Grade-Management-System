package org.university_grade_management_service.dtos;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class AddGradeDto {

    @NotBlank(message = "Can't be blank!")
    @Size(min = 5, message = "Must contain at least 5 characters")
    private String studentIndex;

    @NotNull(message = "Can't be blank")
    @Min(value = 0, message = "Points scored can't be lower then 0")
    @Max(value = 50, message = "Points scored can't be higher than 50")
    private Integer pointsScored;

    @Pattern(regexp = "JANUARY|FEBRUARY|JUNE|JULY|AUGUST|SEPTEMBER", message = "Invalid exam period")
    @NotBlank(message = "Can't be blank")
    private String examPeriod;




}
