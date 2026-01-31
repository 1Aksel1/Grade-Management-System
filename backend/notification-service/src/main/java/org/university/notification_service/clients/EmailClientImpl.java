package org.university.notification_service.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailClientImpl implements EmailClient{

    private JavaMailSender mailSender;
    private boolean emailEnabled = false;

    @Autowired
    public EmailClientImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String email, String subject, String content) {

        if(!emailEnabled) {
            return;
        }

        if(!email.contains("rafdemo")) {

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject(subject);
            message.setText(content);

            mailSender.send(message);

        }

    }


}
