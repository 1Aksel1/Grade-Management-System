package org.university.notification_service.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.university.notification_service.dtos.ChangePasswordNotiDto;
import org.university.notification_service.listeners.helpers.MessageHelper;
import org.university.notification_service.services.ProcessNotificationService;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class ChangePasswordListener {

    private MessageHelper messageHelper;
    private ProcessNotificationService processNotificationService;

    @Autowired
    public ChangePasswordListener(MessageHelper messageHelper, ProcessNotificationService processNotificationService) {
        this.messageHelper = messageHelper;
        this.processNotificationService = processNotificationService;
    }


    @JmsListener(destination = "${destination.changePassword}", concurrency = "4-8")
    public void changePasswordListener(Message message) throws JMSException {

        ChangePasswordNotiDto changePasswordNotiDto = messageHelper.getMessage(message, ChangePasswordNotiDto.class);
        processNotificationService.processChangePasswordRequest(changePasswordNotiDto);

    }



}
