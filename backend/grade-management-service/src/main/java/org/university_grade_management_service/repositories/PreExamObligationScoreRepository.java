package org.university_grade_management_service.repositories;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.university_grade_management_service.model.Course;
import org.university_grade_management_service.model.PreExamObligationScore;

import java.util.List;
import java.util.Optional;

@Repository
public interface PreExamObligationScoreRepository extends JpaRepository<PreExamObligationScore, Long> {

    boolean existsByCourseAndNameAndStudentIndex(Course course, String obligationName, String studentIndex);

    List<PreExamObligationScore> findAllByCourseAndStudentIndex(Course course, String studentIndex);

    List<PreExamObligationScore> findAllByCourse(Course course);

}
