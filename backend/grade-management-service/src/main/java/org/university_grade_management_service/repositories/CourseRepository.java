package org.university_grade_management_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.university_grade_management_service.dtos.CourseGeneralInfoDto;
import org.university_grade_management_service.model.Course;
import org.university_grade_management_service.model.StudentEnrollment;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findByName(String courseName);


    @Query("SELECT c FROM Course c JOIN c.studentEnrollments se WHERE se.id IN :enrollmentIds")
    List<Course> findCoursesByEnrollmentIds(@Param("enrollmentIds") List<Long> enrollmentIds);


}
