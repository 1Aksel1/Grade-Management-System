package org.university_student_client_app.dto;

import lombok.Data;

@Data
public class StudentPreExamScoreDto {

    private String obligationName;
    private Integer pointsScored;

    public StudentPreExamScoreDto(String obligationName, Integer pointsScored) {
        this.obligationName = obligationName;
        this.pointsScored = pointsScored;
    }

    public StudentPreExamScoreDto() {
    }


}
