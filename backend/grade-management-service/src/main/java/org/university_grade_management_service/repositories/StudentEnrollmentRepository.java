package org.university_grade_management_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.university_grade_management_service.model.Course;
import org.university_grade_management_service.model.EnrollmentStatus;
import org.university_grade_management_service.model.StudentEnrollment;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentEnrollmentRepository extends JpaRepository<StudentEnrollment, Long> {

    Optional<StudentEnrollment> findByStudentIndexAndCourse(String studentIndex, Course course);

    List<StudentEnrollment> findAllByStudentIndexAndAndStatus(String studentIndex, EnrollmentStatus enrollmentStatus);

    @Modifying
    @Query("UPDATE StudentEnrollment e SET e.status = :completed WHERE e.course = :course AND e.studentIndex = :studentIndex")
    void setStatusToCompleted(@Param("completed") EnrollmentStatus completed, @Param("course") Course course, @Param("studentIndex") String studentIndex);

    List<StudentEnrollment> findAllByCourse(Course course);


}
