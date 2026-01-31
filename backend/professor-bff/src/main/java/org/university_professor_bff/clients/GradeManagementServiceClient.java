package org.university_professor_bff.clients;

import org.university_professor_bff.dtos.*;
import reactor.core.publisher.Mono;

import java.util.List;

public interface GradeManagementServiceClient {

    public List<CourseGeneralInfoDto> getProfessorCourses(String uri, String authHeader);

    public Mono<CourseCompleteInfo> getCompleteCourseInfo(String uri, Long id, String authHeader);

    public PreExamObligation createPreExamObligation(String uri, String authHeader, PreExamObligationDto preExamObligationDto);

    public PreExamObligationScore createPreExamObligationScore(String uri, String authHeader, PreExamObligationScoreDto preExamObligationScoreDto);

    public Grade createGrade(String uri, String authHeader, AddGradeDto addGradeDto);


}
