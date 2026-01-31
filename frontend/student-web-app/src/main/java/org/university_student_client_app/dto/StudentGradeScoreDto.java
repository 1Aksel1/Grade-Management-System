package org.university_student_client_app.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class StudentGradeScoreDto {

    private String courseName;
    private Integer grade;
    private Integer examPointsScored;
    private String examPeriod;
    private List<StudentPreExamScoreDto> preExamScoreDtos = new ArrayList<>();

    public StudentGradeScoreDto(String courseName, Integer grade, Integer examPointsScored, String examPeriod, List<StudentPreExamScoreDto> preExamScoreDtos) {
        this.courseName = courseName;
        this.grade = grade;
        this.examPointsScored = examPointsScored;
        this.examPeriod = examPeriod;
        this.preExamScoreDtos = preExamScoreDtos;
    }

    public StudentGradeScoreDto() {
    }


}
