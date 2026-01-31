package org.university_admin_bff.services;

import org.university_admin_bff.dtos.ExamPeriod;
import org.university_admin_bff.dtos.ExamPeriodStatusDto;
import org.university_admin_bff.dtos.SingleMessageDto;

import java.util.List;

public interface GradeManagementService {

    public List<ExamPeriod> getAllExamPeriods(String authHeader);

    public SingleMessageDto activateExamPeriod(String authHeader, ExamPeriodStatusDto examPeriodStatusDto);

    public SingleMessageDto deactivateExamPeriod(String authHeader, ExamPeriodStatusDto examPeriodStatusDto);


}
