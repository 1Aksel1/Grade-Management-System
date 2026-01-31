package org.university.notification_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "saved_notifications")
public class SavedNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "type_id")
    private NotificationType notificationType;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private LocalDateTime sentTimeAndDate;

    public SavedNotification(String email, NotificationType notificationType, String subject, String content, LocalDateTime sentTimeAndDate) {
        this.email = email;
        this.notificationType = notificationType;
        this.subject = subject;
        this.content = content;
        this.sentTimeAndDate = sentTimeAndDate;
    }

    public SavedNotification() {
    }
}
