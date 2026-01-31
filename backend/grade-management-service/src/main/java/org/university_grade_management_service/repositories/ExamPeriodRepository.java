package org.university_grade_management_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.university_grade_management_service.model.ExamPeriod;
import org.university_grade_management_service.model.Period;

import java.util.Optional;

@Repository
public interface ExamPeriodRepository extends JpaRepository<ExamPeriod, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE ExamPeriod e SET e.status = true WHERE e.period = :examPeriod AND e.status = false")
    int activateExamPeriod(@Param("examPeriod") Period examPeriod);

    @Modifying
    @Transactional
    @Query("UPDATE ExamPeriod e SET e.status = false WHERE e.period = :examPeriod AND e.status = true")
    int deactivateExamPeriod(@Param("examPeriod") Period examPeriod);

    Optional<ExamPeriod> findByPeriod(Period examPeriod);


}
