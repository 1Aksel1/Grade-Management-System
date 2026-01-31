package org.university_professor_bff.dtos;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CourseCompleteInfo {

    private CourseGeneralInfoDto courseGeneralInfoDto;
    private Long id;
    private String name;
    private String studyProgram;
    private Integer ECTS;
    private List<PreExamObligation> preExamObligations = new ArrayList<>();
    private List<PreExamObligationScore> preExamObligationScores = new ArrayList<>();
    private List<StudentEnrollment> studentEnrollments = new ArrayList<>();
    private List<Grade> grades = new ArrayList<>();

    public CourseCompleteInfo(CourseGeneralInfoDto courseGeneralInfoDto, List<PreExamObligation> preExamObligations, List<PreExamObligationScore> preExamObligationScores, List<StudentEnrollment> studentEnrollments, List<Grade> grades) {
        this.id = courseGeneralInfoDto.getId();
        this.name = courseGeneralInfoDto.getName();
        this.studyProgram = courseGeneralInfoDto.getStudyProgram();
        this.ECTS = courseGeneralInfoDto.getECTS();
        this.preExamObligations = preExamObligations;
        this.preExamObligationScores = preExamObligationScores;
        this.studentEnrollments = studentEnrollments;
        this.grades = grades;
        System.out.println("Hej");
    }


}
