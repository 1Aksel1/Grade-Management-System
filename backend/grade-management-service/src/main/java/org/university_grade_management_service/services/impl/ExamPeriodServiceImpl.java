package org.university_grade_management_service.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.university_grade_management_service.exceptions.ExamPeriodNotActiveException;
import org.university_grade_management_service.model.ExamPeriod;
import org.university_grade_management_service.model.Period;
import org.university_grade_management_service.repositories.ExamPeriodRepository;
import org.university_grade_management_service.services.ExamPeriodService;
import org.university_grade_management_service.services.SendNotificationsService;

import java.util.List;
import java.util.Optional;

@Service
public class ExamPeriodServiceImpl implements ExamPeriodService {

    private ExamPeriodRepository examPeriodRepository;
    private SendNotificationsService sendNotificationsService;

    @Autowired
    public ExamPeriodServiceImpl(ExamPeriodRepository examPeriodRepository, SendNotificationsService sendNotificationsService) {
        this.examPeriodRepository = examPeriodRepository;
        this.sendNotificationsService = sendNotificationsService;
    }


    public String activateExamPeriod(Period examPeriod) {

        int status = examPeriodRepository.activateExamPeriod(examPeriod);

        if(status == 1) {
            sendNotificationsService.sendExamPeriodActiveNotification(examPeriod);
            return "Exam period activated successfully.";
        }

        return "Exam period was already active.";

    }


    public String deactivateExamPeriod(Period examPeriod) {

        int status = examPeriodRepository.deactivateExamPeriod(examPeriod);

        if(status == 1) {
            return "Exam period deactivated successfully.";
        }

        return "Exam period was already inactive.";

    }

    public void examPeriodIsActive(Period examPeriod) {

        Optional<ExamPeriod> examPeriodOptional = examPeriodRepository.findByPeriod(examPeriod);

        if(!examPeriodOptional.isPresent()) {
            throw new ExamPeriodNotActiveException("Exam period is not found!");
        }

        if(!examPeriodOptional.get().getStatus()) {
            throw new ExamPeriodNotActiveException("Exam period is not active!");
        }

    }

    @Override
    public List<ExamPeriod> findAll() {
        return examPeriodRepository.findAll();
    }


}
