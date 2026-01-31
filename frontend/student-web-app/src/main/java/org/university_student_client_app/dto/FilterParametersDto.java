package org.university_student_client_app.dto;

import lombok.Data;

@Data
public class FilterParametersDto {

    private boolean accountActivation;
    private boolean passwordChange;
    private boolean examPeriodActivation;
    private boolean gradeAdded;
    private String dateFrom;
    private String dateTo;

    public FilterParametersDto(boolean accountActivation, boolean passwordChange, boolean examPeriodActivation, boolean gradeAdded) {
        this.accountActivation = accountActivation;
        this.passwordChange = passwordChange;
        this.examPeriodActivation = examPeriodActivation;
        this.gradeAdded = gradeAdded;
    }

    public FilterParametersDto() {

    }

}
