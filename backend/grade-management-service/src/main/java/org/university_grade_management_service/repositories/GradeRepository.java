package org.university_grade_management_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.university_grade_management_service.model.Course;
import org.university_grade_management_service.model.Grade;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {

    List<Grade> findAllByStudentIndex(String studentIndex);

    List<Grade> findAllByCourse(Course course);


}
