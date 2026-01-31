package org.university_admin_bff.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.university_admin_bff.clients.GradeManagementServiceClient;
import org.university_admin_bff.dtos.ExamPeriod;
import org.university_admin_bff.dtos.ExamPeriodStatusDto;
import org.university_admin_bff.dtos.SingleMessageDto;
import org.university_admin_bff.utils.JwtUtil;

import java.util.List;

@Service
public class GradeManagementServiceImpl implements  GradeManagementService {

    private GradeManagementServiceClient gradeManagementServiceClient;

    @Autowired
    public GradeManagementServiceImpl(GradeManagementServiceClient gradeManagementServiceClient) {
        this.gradeManagementServiceClient = gradeManagementServiceClient;
    }

    @Override
    public List<ExamPeriod> getAllExamPeriods(String authHeader) {

        String uri = "/examPeriod/all";
        return gradeManagementServiceClient.getAllExamPeriods(uri, authHeader);
    }

    @Override
    public SingleMessageDto activateExamPeriod(String authHeader, ExamPeriodStatusDto examPeriodStatusDto) {

        String uri = "/examPeriod/activate";
        return gradeManagementServiceClient.activateExamPeriod(uri, authHeader, examPeriodStatusDto);
    }

    @Override
    public SingleMessageDto deactivateExamPeriod(String authHeader, ExamPeriodStatusDto examPeriodStatusDto) {

        String uri = "/examPeriod/deactivate";
        return gradeManagementServiceClient.deactivateExamPeriod(uri, authHeader, examPeriodStatusDto);
    }

}
