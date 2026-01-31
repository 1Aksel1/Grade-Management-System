package org.university_student_bff.dtos;

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
