package org.university.user_service.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "registration_requests")
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String linkIdentifier;

    @Column(nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserType userType;

    @Column(nullable = false, unique = true)
    private String userEmail;

    @Column(nullable = false)
    private LocalDateTime creationTime;

    @Column(nullable = false)
    private String sessionId;

    public Registration(String linkIdentifier, Long userId, UserType userType, String userEmail, LocalDateTime creationTime, String sessionId) {
        this.linkIdentifier = linkIdentifier;
        this.userId = userId;
        this.userType = userType;
        this.userEmail = userEmail;
        this.creationTime = creationTime;
        this.sessionId = sessionId;
    }

    public Registration() {
    }

}
