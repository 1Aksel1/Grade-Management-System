package org.university.notification_service.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.university.notification_service.dtos.ActivateRegistrationNotiDto;
import org.university.notification_service.listeners.helpers.MessageHelper;
import org.university.notification_service.services.ProcessNotificationService;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class RegisterActivationListener {

    private MessageHelper messageHelper;
    private ProcessNotificationService processNotificationService;

    @Autowired
    public RegisterActivationListener(MessageHelper messageHelper, ProcessNotificationService processNotificationService) {
        this.messageHelper = messageHelper;
        this.processNotificationService = processNotificationService;
    }


    @JmsListener(destination = "${destination.registerActivation}", concurrency = "4-8")
    public void registerActivationListener(Message message) throws JMSException {

        ActivateRegistrationNotiDto activateRegistrationNotiDto = messageHelper.getMessage(message, ActivateRegistrationNotiDto.class);
        processNotificationService.processRegistrationActivation(activateRegistrationNotiDto);

    }



}
