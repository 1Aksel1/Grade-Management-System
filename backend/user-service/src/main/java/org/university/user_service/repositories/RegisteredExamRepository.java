package org.university.user_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.university.user_service.model.Period;
import org.university.user_service.model.RegisteredExam;
import org.university.user_service.model.Student;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegisteredExamRepository extends JpaRepository<RegisteredExam, Long> {


    @Query("SELECT r FROM RegisteredExam r WHERE r.student.id = :studentId and r.courseName = :courseName")
    Optional<RegisteredExam> isExamRegistered(@Param("studentId") Long studentId, @Param("courseName") String courseName);

    Boolean existsByCourseNameAndStudentAndExamPeriod(String courseName, Student student, Period examPeriod);

    @Modifying
    @Query("DELETE FROM RegisteredExam r WHERE r.student = :student AND r.courseName = :courseName AND r.examPeriod = :examPeriod")
    int deleteRegisteredExamIfExists(@Param("student") Student student, @Param("courseName") String courseName, @Param("examPeriod") Period examPeriod);


    List<RegisteredExam> findAllByStudent(Student student);

}
