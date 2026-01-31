package org.university_professor_bff.services;

import org.university_professor_bff.dtos.*;
import reactor.core.publisher.Mono;

import java.util.List;

public interface GradeManagementService {

    public List<CourseGeneralInfoDto> getProfessorCourses(String authHeader);

    public Mono<CourseCompleteInfo> getSingleCourse(String authHeader, Long id);

    public PreExamObligation createPreExamObligation(String authHeader, Long id, PreExamObligationDto preExamObligationDto);

    public PreExamObligationScore createPreExamObligationScore(String authHeader, Long id, PreExamObligationScoreDto preExamObligationScoreDto);

    public Grade createGrade(String authHeader, Long id, AddGradeDto addGradeDto);



}
