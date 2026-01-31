package org.university_grade_management_service.dtos;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class PreExamObligationDto {

    @NotBlank(message = "Can't be blank")
    @Size(min = 2, max = 35, message = "Pre exam obligation name must be valid")
    private String name;

    @NotNull(message = "Can't be blank")
    @Min(value = 1, message = "Max points can't be lower then 1")
    @Max(value = 70, message = "Max points can't be higher than 70")
    private Integer maxPoints;

}
