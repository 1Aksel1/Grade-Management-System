package org.university_grade_management_service.services;

import org.university_grade_management_service.model.ExamPeriod;
import org.university_grade_management_service.model.Period;

import java.util.List;

public interface ExamPeriodService {

    public String activateExamPeriod(Period examPeriod);

    public void examPeriodIsActive(Period period);

    public String deactivateExamPeriod(Period examPeriod);

    public List<ExamPeriod> findAll();

}
