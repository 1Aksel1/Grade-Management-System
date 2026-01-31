package org.university_student_client_app.dto;

import lombok.Data;

@Data
public class CourseGeneralInfoData {

    private Long id;
    private String name;
    private String studyProgram;
    private Integer ECTS;

    public CourseGeneralInfoData(Long id, String name, String studyProgram, Integer ECTS) {
        this.id = id;
        this.name = name;
        this.studyProgram = studyProgram;
        this.ECTS = ECTS;
    }

    public CourseGeneralInfoData() {
    }

}
