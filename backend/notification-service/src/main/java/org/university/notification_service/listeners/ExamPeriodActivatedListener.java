package org.university.notification_service.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.university.notification_service.dtos.ExamPeriodActivatedNotiDto;
import org.university.notification_service.listeners.helpers.MessageHelper;
import org.university.notification_service.services.ProcessNotificationService;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class ExamPeriodActivatedListener {

    private MessageHelper messageHelper;
    private ProcessNotificationService processNotificationService;

    @Autowired
    public ExamPeriodActivatedListener(MessageHelper messageHelper, ProcessNotificationService processNotificationService) {
        this.messageHelper = messageHelper;
        this.processNotificationService = processNotificationService;
    }



    @JmsListener(destination = "${destination.examPeriodActivated}", concurrency = "4-8")
    public void examPeriodActivatedListener(Message message) throws JMSException {

        ExamPeriodActivatedNotiDto examPeriodActivatedNotiDto = messageHelper.getMessage(message, ExamPeriodActivatedNotiDto.class);
        processNotificationService.processExamPeriodActivation(examPeriodActivatedNotiDto);

    }



}
