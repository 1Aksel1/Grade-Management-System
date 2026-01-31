package org.university_grade_management_service.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.university_grade_management_service.dtos.ExamPeriodActivatedNotiDto;
import org.university_grade_management_service.dtos.GradeInfoNotiDto;
import org.university_grade_management_service.dtos.StudentPreExamScoreDto;
import org.university_grade_management_service.helpers.MessageHelper;
import org.university_grade_management_service.model.Period;
import org.university_grade_management_service.model.PreExamObligationScore;
import org.university_grade_management_service.services.SendNotificationsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class SendNotificationsServiceImpl implements SendNotificationsService {

    private JmsTemplate jmsTemplate;
    private MessageHelper messageHelper;

    @Value("${destination.examPeriodActivated}")
    private String examPeriodActivatedDestination;

    @Value("${notificationType.examPeriodActivated}")
    private String examPeriodActivatedNotificationType;

    @Value("${destination.gradeAdded}")
    private String gradeAddedDestination;

    @Value("${notificationType.gradeAdded}")
    private String gradeAddedNotificationType;



    @Autowired
    public SendNotificationsServiceImpl(JmsTemplate jmsTemplate, MessageHelper messageHelper) {
        this.jmsTemplate = jmsTemplate;
        this.messageHelper = messageHelper;
    }

    public void sendExamPeriodActiveNotification(Period examPeriod) {

        String examPeriodStr = examPeriod.toString().toLowerCase(Locale.ROOT);

        ExamPeriodActivatedNotiDto examPeriodActivatedNotiDto = new ExamPeriodActivatedNotiDto(examPeriodActivatedNotificationType, examPeriodStr);
        String message = messageHelper.createTextMessage(examPeriodActivatedNotiDto);

        jmsTemplate.convertAndSend(examPeriodActivatedDestination, message);

    }

    public void sendGradeInfoNotification(String studentIndex, String courseName, Integer grade, Integer examPoints, List<PreExamObligationScore> preExamObligationScores) {

        List<StudentPreExamScoreDto> preExamScoreDtos = new ArrayList<>();

        preExamObligationScores.forEach(preExamObligationScore -> {
            preExamScoreDtos.add(new StudentPreExamScoreDto(preExamObligationScore.getName(), preExamObligationScore.getPointsScored()));
        });

        GradeInfoNotiDto gradeInfoNotiDto = new GradeInfoNotiDto(studentIndex, courseName, gradeAddedNotificationType, grade, examPoints, preExamScoreDtos);
        String message = messageHelper.createTextMessage(gradeInfoNotiDto);

        jmsTemplate.convertAndSend(gradeAddedDestination, message);

    }


}
