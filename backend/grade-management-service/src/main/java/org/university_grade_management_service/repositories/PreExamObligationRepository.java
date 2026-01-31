package org.university_grade_management_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.university_grade_management_service.model.Course;
import org.university_grade_management_service.model.PreExamObligation;

import java.util.List;
import java.util.Optional;

@Repository
public interface PreExamObligationRepository extends JpaRepository<PreExamObligation, Long> {

    Optional<PreExamObligation> findByCourseAndName(Course course, String obligationName);

    List<PreExamObligation> findAllByCourse(Course course);



}
