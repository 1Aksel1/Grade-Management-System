package org.university_admin_bff.clients;

import org.university_admin_bff.dtos.ExamPeriod;
import org.university_admin_bff.dtos.ExamPeriodStatusDto;
import org.university_admin_bff.dtos.SingleMessageDto;

import java.util.List;

public interface GradeManagementServiceClient {

    public List<ExamPeriod> getAllExamPeriods(String uri, String authHeader);

    public SingleMessageDto activateExamPeriod(String uri, String authHeader, ExamPeriodStatusDto examPeriodStatusDto);

    public SingleMessageDto deactivateExamPeriod(String uri, String authHeader, ExamPeriodStatusDto examPeriodStatusDto);


}
