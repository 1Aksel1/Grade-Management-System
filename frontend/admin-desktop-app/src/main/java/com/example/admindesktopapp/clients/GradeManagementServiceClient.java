package com.example.admindesktopapp.clients;

import com.example.admindesktopapp.dto.ExamPeriod;
import com.example.admindesktopapp.dto.ExamPeriodStatusDto;
import com.example.admindesktopapp.dto.SingleMessageDto;

import java.util.List;

public interface GradeManagementServiceClient {

    public List<ExamPeriod> getAllExamPeriods();

    public SingleMessageDto activateExamPeriod(ExamPeriodStatusDto examPeriodStatusDto);

    public SingleMessageDto deactivateExamPeriod(ExamPeriodStatusDto examPeriodStatusDto);


}
