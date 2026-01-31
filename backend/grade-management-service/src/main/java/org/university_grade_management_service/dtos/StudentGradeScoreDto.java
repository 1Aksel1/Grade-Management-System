package org.university_grade_management_service.dtos;

import lombok.Data;
import org.university_grade_management_service.model.ExamPeriod;
import org.university_grade_management_service.model.Period;

import java.util.ArrayList;
import java.util.List;

@Data
public class StudentGradeScoreDto {

    private String courseName;
    private Integer grade;
    private Integer examPointsScored;
    private Period examPeriod;
    private List<StudentPreExamScoreDto> preExamScoreDtos = new ArrayList<>();

    public StudentGradeScoreDto(String courseName, Integer grade, Integer examPointsScored, Period examPeriod) {
        this.courseName = courseName;
        this.grade = grade;
        this.examPointsScored = examPointsScored;
        this.examPeriod = examPeriod;
    }

    public StudentGradeScoreDto() {
    }


}
