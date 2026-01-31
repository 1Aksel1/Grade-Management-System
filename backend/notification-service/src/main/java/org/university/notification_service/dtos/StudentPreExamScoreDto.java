package org.university.notification_service.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class StudentPreExamScoreDto {

    @NotBlank(message = "Can't be blank")
    private String obligationName;

    @NotNull(message = "Can't be null")
    private Integer pointsScored;

    public StudentPreExamScoreDto(String obligationName, Integer pointsScored) {
        this.obligationName = obligationName;
        this.pointsScored = pointsScored;
    }

    public StudentPreExamScoreDto() {
    }

    @Override
    public String toString() {
        return "\n" +
                "obligationName = " + obligationName +
                '\n' +
                "pointsScored = " + pointsScored;
    }
}
