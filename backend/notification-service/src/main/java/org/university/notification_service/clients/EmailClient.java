package org.university.notification_service.clients;

public interface EmailClient {

    public void sendEmail(String email, String subject, String content);


}
