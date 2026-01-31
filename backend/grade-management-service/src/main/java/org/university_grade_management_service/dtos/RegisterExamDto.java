package org.university_grade_management_service.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class RegisterExamDto {

    private Long studentId;
    private String courseName;
    private String examPeriod;

    public RegisterExamDto(Long studentId, String courseName, String examPeriod) {
        this.studentId = studentId;
        this.courseName = courseName;
        this.examPeriod = examPeriod;
    }

    public RegisterExamDto() {
    }

}