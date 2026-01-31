package org.university.user_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.university.user_service.model.Professor;

import java.util.Optional;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    Optional<Professor> findProfessorByEmail(String email);

    Optional<Professor> findProfessorByUsernameOrTelephoneNumber(String username, String telephoneNumber);

    @Modifying
    @Query("UPDATE Professor p SET p.registered = true WHERE p.id = :id")
    void activateProfessorRegistration(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Professor p SET p.password = :password WHERE p.id = :id")
    void setNewPassword(@Param("id") Long id, @Param("password") String password);

}
