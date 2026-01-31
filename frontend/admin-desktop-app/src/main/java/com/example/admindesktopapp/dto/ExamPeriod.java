package com.example.admindesktopapp.dto;

public class ExamPeriod {

    private Long id;
    private String period;
    private Boolean status;

    public ExamPeriod(Long id, String period, Boolean status) {
        this.id = id;
        this.period = period;
        this.status = status;
    }

    public ExamPeriod() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
