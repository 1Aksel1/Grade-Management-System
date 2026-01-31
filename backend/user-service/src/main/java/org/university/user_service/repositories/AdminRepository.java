package org.university.user_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.university.user_service.model.Admin;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findAdminByEmail(String email);

    Optional<Admin> findAdminByUsernameOrTelephoneNumber(String username, String telephoneNumber);

    Optional<Admin> findAdminByIdAndPassword(Long id, String password);

    @Modifying
    @Query("UPDATE Admin a SET a.password = :password WHERE a.id = :id")
    void setNewPassword(@Param("id") Long id, @Param("password") String password);

}
