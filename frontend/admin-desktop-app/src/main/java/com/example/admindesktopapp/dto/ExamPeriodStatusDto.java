package com.example.admindesktopapp.dto;

public class ExamPeriodStatusDto {

    private String examPeriod;

    public ExamPeriodStatusDto(String examPeriod) {
        this.examPeriod = examPeriod;
    }

    public ExamPeriodStatusDto() {
    }


    public String getExamPeriod() {
        return examPeriod;
    }

    public void setExamPeriod(String examPeriod) {
        this.examPeriod = examPeriod;
    }

}
