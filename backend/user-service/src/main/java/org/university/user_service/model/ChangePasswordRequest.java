package org.university.user_service.model;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "change_password_requests")
public class ChangePasswordRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String linkIdentifier;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private UserType userType;

    @Column(nullable = false)
    private String newPassword;

    @Column(nullable = false)
    private LocalDateTime creationTime;

    public ChangePasswordRequest(String linkIdentifier, Long userId, UserType userType, String newPassword, LocalDateTime creationTime) {
        this.linkIdentifier = linkIdentifier;
        this.userId = userId;
        this.userType = userType;
        this.newPassword = newPassword;
        this.creationTime = creationTime;
    }

    public ChangePasswordRequest() {
    }

}
