package com.example.admindesktopapp.services;

import com.example.admindesktopapp.dto.ExamPeriod;
import com.example.admindesktopapp.dto.SingleMessageDto;

import java.util.List;

public interface GradeManagementService {

    List<ExamPeriod> getAllExamPeriods();

    public SingleMessageDto activateExamPeriod(String examPeriod);

    public SingleMessageDto deactivateExamPeriod(String examPeriod);



}
