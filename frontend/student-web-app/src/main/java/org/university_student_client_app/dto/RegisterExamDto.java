package org.university_student_client_app.dto;

import lombok.Data;

@Data
public class RegisterExamDto {

    private String courseName;
    private String examPeriod;

    public RegisterExamDto(String courseName, String examPeriod) {
        this.courseName = courseName;
        this.examPeriod = examPeriod;
    }

    public RegisterExamDto() {
    }

}
