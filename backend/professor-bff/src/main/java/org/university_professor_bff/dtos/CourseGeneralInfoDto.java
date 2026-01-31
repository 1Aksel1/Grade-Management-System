package org.university_professor_bff.dtos;

import lombok.Data;

@Data
public class CourseGeneralInfoDto {


    private Long id;
    private String name;
    private String studyProgram;
    private Integer ECTS;

    public CourseGeneralInfoDto(Long id, String name, String studyProgram, Integer ECTS) {
        this.id = id;
        this.name = name;
        this.studyProgram = studyProgram;
        this.ECTS = ECTS;
    }

    public CourseGeneralInfoDto() {
    }


}
