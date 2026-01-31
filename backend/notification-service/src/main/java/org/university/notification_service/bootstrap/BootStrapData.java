package org.university.notification_service.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.stereotype.Component;
import org.university.notification_service.model.NotificationType;
import org.university.notification_service.model.SavedNotification;
import org.university.notification_service.repositories.NotificationTypeRepository;
import org.university.notification_service.repositories.SavedNotificationRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class BootStrapData implements CommandLineRunner {

    private NotificationTypeRepository notificationTypeRepository;
    private SavedNotificationRepository savedNotificationRepository;

    @Autowired
    public BootStrapData(NotificationTypeRepository notificationTypeRepository, SavedNotificationRepository savedNotificationRepository) {
        this.notificationTypeRepository = notificationTypeRepository;
        this.savedNotificationRepository = savedNotificationRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        List<NotificationType> notificationTypes = new ArrayList<>();

        NotificationType n1 = new NotificationType("activation_email", "Account activation", "Hi %s %s! To verify the registration please click on the following link: %s", "[\"name\", \"surname\", \"activationLink\"]");
        NotificationType n2 = new NotificationType("password_change", "Password change request", "Hi %s! To verify the password change please click on the following link: %s", "[\"username\", \"confirmationLink\"]");
        NotificationType n3 = new NotificationType("exam_period_activated", "Exam period activation", "Hi %s, the exam period %s is now active and you can register exams. Good luck!", "[\"username\", \"examPeriod\"]");
        NotificationType n4 = new NotificationType("grade_added", "Grade information", "Congratulations %s! You have passed the exam of the course %s and received the grade %d. Number of points you have achieved on the exam is %d. Points on pre exam obligations: ", "[\"name\", \"courseName\", \"grade\", \"examPoints\", \"preExamScoreDtos\"]");

        notificationTypes.add(n1);
        notificationTypes.add(n2);
        notificationTypes.add(n3);
        notificationTypes.add(n4);

        notificationTypeRepository.saveAll(notificationTypes);


        List<SavedNotification> savedNotifications = new ArrayList<>();

        SavedNotification sn1 = new SavedNotification("professor@demo.com", n1, n1.getSubject(), "Hi Ivana Ivanović! To verify the registration please click on the following link: http://localhost:8080/api/activate?token=cfe8faac-dd5c-4777-88d5-afc1c8524b2b", LocalDateTime.of(2024, 8, 16, 17, 25));
        SavedNotification sn2 = new SavedNotification("professor2@rafdemo.rs", n1, n1.getSubject(), "Hi Milan Milanović! To verify the registration please click on the following link: http://localhost:8080/api/activate?token=d5fd61ad-c7cf-4745-a2b2-af463f54ef06", LocalDateTime.of(2024, 6, 2, 12, 00));
        SavedNotification sn3 = new SavedNotification("lzarkovic1021ri@rafdemo.rs", n3, n3.getSubject(), "Hi lidijaŽarković, the exam period january is now active and you can register exams. Good luck!", LocalDateTime.of(2024, 9, 9, 9, 00));
        SavedNotification sn4 = new SavedNotification("student@demo.com", n3, n3.getSubject(), "Hi akselVlaovic, the exam period january is now active and you can register exams. Good luck!", LocalDateTime.of(2024, 9, 9, 9, 00));
        SavedNotification sn5 = new SavedNotification("mmarkovic2719ri@rafdemo.rs", n3, n3.getSubject(), "Hi markoMarkovic, the exam period january is now active and you can register exams. Good luck!", LocalDateTime.of(2024, 9, 9, 9, 00));
        SavedNotification sn6 = new SavedNotification("professor@demo.com", n2, n2.getSubject(), "Hi professor1! To verify the password change please click on the following link: http://localhost:8080/api/professor/changePassword?token=193e85d9-21f8-4733-b990-c12269bbbfc8", LocalDateTime.of(2024, 5, 4, 10, 10));
        SavedNotification sn7 = new SavedNotification("mmarkovic2719ri@rafdemo.rs", n2, n2.getSubject(), "Hi markoMarković! To verify the password change please click on the following link: http://localhost:8080/api/student/changePassword?token=f1551178-0def-4661-89f4-ac2e5e9e17e2", LocalDateTime.of(2024, 3, 1, 16, 43));
        SavedNotification sn8 = new SavedNotification("student@demo.com", n2, n2.getSubject(), "Hi akselVlaovic! To verify the password change please click on the following link: http://localhost:8080/api/student/changePassword?token=411cdbb5-f02f-4fc0-b337-5d699ef2075d", LocalDateTime.of(2024, 2, 15, 15, 34));
        SavedNotification sn9 = new SavedNotification("botovic11120ri@rafdemo.rs", n4, n4.getSubject(), "Congratulations Bojan! You have passed the exam of the course Mikroservisne aplikacije and received the grade 10. Number of points you have achieved on the exam is 40. Points on pre exam obligations: \\nobligationName = kolokvijum1\\npointsScored = 28\\nobligationName = kolokvijum2\\npointsScored = 24", LocalDateTime.of(2024, 1, 29, 17, 00));
        SavedNotification sn11 = new SavedNotification("mmarkovic2719ri@rafdemo.rs", n4, n4.getSubject(), "Congratulations Marko! You have passed the exam of the course Razvoj servisa u oblaku and received the grade 9. Number of points you have achieved on the exam is 40. Points on pre exam obligations: \\nobligationName = test\\npointsScored = 15\\nobligationName = kolokvijum\\npointsScored = 30", LocalDateTime.of(2024, 1, 29, 17, 07));
        SavedNotification sn12 = new SavedNotification("mmarkovic2719ri@rafdemo.rs", n4, n4.getSubject(), "Congratulations Marko! You have passed the exam of the course Razvoj servisa u oblaku and received the grade 9. Number of points you have achieved on the exam is 40. Points on pre exam obligations: \\nobligationName = test\\npointsScored = 15\\nobligationName = kolokvijum\\npointsScored = 30", LocalDateTime.of(2024, 1, 29, 17, 07));
        SavedNotification sn13 = new SavedNotification("student@demo.com", n3, n3.getSubject(), "Hi akselVlaovic, the exam period january is now active and you can register exams. Good luck!", LocalDateTime.of(2024, 9, 9, 9, 00));
        SavedNotification sn14 = new SavedNotification("mmarkovic2719ri@rafdemo.rs", n3, n3.getSubject(), "Hi markoMarkovic, the exam period january is now active and you can register exams. Good luck!", LocalDateTime.of(2024, 9, 9, 9, 00));


        savedNotifications.add(sn1);
        savedNotifications.add(sn2);
        savedNotifications.add(sn3);
        savedNotifications.add(sn4);
        savedNotifications.add(sn5);
        savedNotifications.add(sn6);
        savedNotifications.add(sn7);
        savedNotifications.add(sn8);
        savedNotifications.add(sn9);
        savedNotifications.add(sn11);
        savedNotifications.add(sn12);
        savedNotifications.add(sn13);
        savedNotifications.add(sn14);

        savedNotificationRepository.saveAll(savedNotifications);



    }


}
