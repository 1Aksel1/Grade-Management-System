package org.university.notification_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "notification_types")
public class NotificationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String typeName;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false)
    private String template;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String parameters;

    @JsonIgnore
    @OneToMany(mappedBy = "notificationType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SavedNotification> savedNotifications = new ArrayList<>();


    public NotificationType(String typeName, String subject, String template, String parameters) {
        this.typeName = typeName;
        this.subject = subject;
        this.template = template;
        this.parameters = parameters;
    }

    public NotificationType() {
    }
}
