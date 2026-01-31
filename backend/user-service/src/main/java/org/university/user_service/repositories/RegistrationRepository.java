package org.university.user_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.university.user_service.model.Registration;
import org.university.user_service.model.UserType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    Optional<Registration> findRegistrationByLinkIdentifier(String linkIdentifier);

    Optional<Registration> findRegistrationBySessionId(String sessionId);

    Optional<Registration> findRegistrationByUserIdAndUserType(Long userId, UserType userType);

    @Query("SELECT r from Registration r where r.creationTime <= :expireLimit")
    List<Registration> findExpiredRegistrations(@Param("expireLimit") LocalDateTime expireLimit);


}
