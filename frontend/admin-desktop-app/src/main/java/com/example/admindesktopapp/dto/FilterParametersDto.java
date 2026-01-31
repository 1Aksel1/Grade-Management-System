package com.example.admindesktopapp.dto;

public class FilterParametersDto {

    private boolean accountActivation;
    private boolean passwordChange;
    private boolean examPeriodActivation;
    private boolean gradeAdded;
    private String email;
    private String dateFrom;
    private String dateTo;

    public FilterParametersDto(boolean accountActivation, boolean passwordChange, boolean examPeriodActivation, boolean gradeAdded, String email, String dateFrom, String dateTo) {
        this.accountActivation = accountActivation;
        this.passwordChange = passwordChange;
        this.examPeriodActivation = examPeriodActivation;
        this.gradeAdded = gradeAdded;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.email = email;
    }

    public FilterParametersDto() {
    }

    public boolean isAccountActivation() {
        return accountActivation;
    }

    public void setAccountActivation(boolean accountActivation) {
        this.accountActivation = accountActivation;
    }

    public boolean isPasswordChange() {
        return passwordChange;
    }

    public void setPasswordChange(boolean passwordChange) {
        this.passwordChange = passwordChange;
    }

    public boolean isExamPeriodActivation() {
        return examPeriodActivation;
    }

    public void setExamPeriodActivation(boolean examPeriodActivation) {
        this.examPeriodActivation = examPeriodActivation;
    }

    public boolean isGradeAdded() {
        return gradeAdded;
    }

    public void setGradeAdded(boolean gradeAdded) {
        this.gradeAdded = gradeAdded;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
