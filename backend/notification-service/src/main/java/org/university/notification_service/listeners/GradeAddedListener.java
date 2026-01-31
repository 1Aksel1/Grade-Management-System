package org.university.notification_service.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.university.notification_service.dtos.GradeInfoNotiDto;
import org.university.notification_service.listeners.helpers.MessageHelper;
import org.university.notification_service.services.ProcessNotificationService;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class GradeAddedListener {

    private MessageHelper messageHelper;
    private ProcessNotificationService processNotificationService;

    @Autowired
    public GradeAddedListener(MessageHelper messageHelper, ProcessNotificationService processNotificationService) {
        this.messageHelper = messageHelper;
        this.processNotificationService = processNotificationService;
    }

    @JmsListener(destination = "${destination.gradeAdded}", concurrency = "4-8")
    public void gradeAddedListener(Message message) throws JMSException {

        GradeInfoNotiDto gradeInfoNotiDto = messageHelper.getMessage(message, GradeInfoNotiDto.class);
        processNotificationService.processGradeAdded(gradeInfoNotiDto);

    }


}
