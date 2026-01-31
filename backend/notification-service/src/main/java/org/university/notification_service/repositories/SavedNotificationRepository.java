package org.university.notification_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
import org.university.notification_service.model.NotificationType;
import org.university.notification_service.model.SavedNotification;

import java.time.LocalDateTime;
import java.util.List;

@NoRepositoryBean
public interface SavedNotificationRepository extends JpaRepository<SavedNotification, Long> {

    List<SavedNotification> findAllForAdmin(String email, List<Long> typeIds, LocalDateTime fromDate, LocalDateTime toDate);

    List<SavedNotification> findAllForStudentOrProfessor(String email, List<Long> typeIds, LocalDateTime fromDate, LocalDateTime toDate);



}
