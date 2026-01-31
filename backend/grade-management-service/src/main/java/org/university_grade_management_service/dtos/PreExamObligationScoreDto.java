package org.university_grade_management_service.dtos;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class PreExamObligationScoreDto {

    @NotBlank(message = "Can't be blank")
    @Size(min = 2, max = 35, message = "Pre exam obligation name must be valid")
    private String name;

    @NotNull(message = "Can't be blank")
    @Min(value = 0, message = "Points scored can't be lower then 0")
    @Max(value = 70, message = "Points scored can't be higher than 70")
    private Integer pointsScored;

    @NotBlank(message = "Can't be blank!")
    @Size(min = 5, message = "Must contain at least 5 characters")
    private String studentIndex;



}
