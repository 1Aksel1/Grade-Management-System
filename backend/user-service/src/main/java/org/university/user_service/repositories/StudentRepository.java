package org.university.user_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import org.university.user_service.dtos.StudentNameAndEmailDto;
import org.university.user_service.dtos.StudentUsernameAndEmailDto;
import org.university.user_service.model.Student;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findStudentByEmail(String email);

    Optional<Student> findStudentByUsernameOrTelephoneNumber(String username, String telephoneNumber);

    Optional<Student> findStudentByIndexNumber(String indexNumber);

    @Modifying
    @Query("UPDATE Student s SET s.registered = true WHERE s.id = :id")
    void activateStudentRegistration(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Student s SET s.password = :password WHERE s.id = :id")
    void setNewPassword(@Param("id") Long id, @Param("password") String password);


    @Query("SELECT new org.university.user_service.dtos.StudentUsernameAndEmailDto(s.username, s.email) FROM Student s")
    List<StudentUsernameAndEmailDto> findStudentUsernamesAndEmails();

    @Query("SELECT new org.university.user_service.dtos.StudentNameAndEmailDto(s.name, s.email) FROM Student s WHERE s.indexNumber = :studentIndex")
    StudentNameAndEmailDto findStudentNameAndEmail(@Param("studentIndex") String studentIndex);




}
