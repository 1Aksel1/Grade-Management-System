package com.example.admindesktopapp.services;

import com.example.admindesktopapp.clients.GradeManagementServiceClient;
import com.example.admindesktopapp.dto.ExamPeriod;
import com.example.admindesktopapp.dto.ExamPeriodStatusDto;
import com.example.admindesktopapp.dto.SingleMessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeManagementServiceImpl implements GradeManagementService {

    private GradeManagementServiceClient gradeManagementServiceClient;

    @Autowired
    public GradeManagementServiceImpl(GradeManagementServiceClient gradeManagementServiceClient) {
        this.gradeManagementServiceClient = gradeManagementServiceClient;
    }

    @Override
    public List<ExamPeriod> getAllExamPeriods() {
        return gradeManagementServiceClient.getAllExamPeriods();
    }

    @Override
    public SingleMessageDto activateExamPeriod(String examPeriod) {

        if(examPeriod == null) {
            throw new RuntimeException("Please select an exam period!");
        }

        return gradeManagementServiceClient.activateExamPeriod(new ExamPeriodStatusDto(examPeriod));

    }

    @Override
    public SingleMessageDto deactivateExamPeriod(String examPeriod) {

        if(examPeriod == null) {
            throw new RuntimeException("Please select an exam period!");
        }

        return gradeManagementServiceClient.deactivateExamPeriod(new ExamPeriodStatusDto(examPeriod));

    }
}
