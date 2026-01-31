package org.university.user_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.university.user_service.model.ChangePasswordRequest;
import org.university.user_service.model.UserType;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ChangePasswordRequestRepository extends JpaRepository<ChangePasswordRequest, Long> {

    Optional<ChangePasswordRequest> findChangePasswordRequestByUserIdAndUserType(Long userId, UserType userType);

    Optional<ChangePasswordRequest> findChangePasswordRequestByLinkIdentifier(String linkIdentifier);

    @Modifying
    @Query("DELETE FROM ChangePasswordRequest r where r.creationTime <= :expiredTime")
    void deleteExpiredRequests(@Param("expiredTime") LocalDateTime expiredTime);



}
