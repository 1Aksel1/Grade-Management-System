package org.university_professor_bff.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.university_professor_bff.clients.GradeManagementServiceClient;
import org.university_professor_bff.dtos.*;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class GradeManagementServiceImpl implements GradeManagementService {

    private GradeManagementServiceClient gradeManagementServiceClient;

    @Autowired
    public GradeManagementServiceImpl(GradeManagementServiceClient gradeManagementServiceClient) {
        this.gradeManagementServiceClient = gradeManagementServiceClient;
    }

    @Override
    public List<CourseGeneralInfoDto> getProfessorCourses(String authHeader) {

        String uri = "/courses";
        return gradeManagementServiceClient.getProfessorCourses(uri, authHeader);

    }

    @Override
    public Mono<CourseCompleteInfo> getSingleCourse(String authHeader, Long id) {

        String uri = "/courses/";
        return gradeManagementServiceClient.getCompleteCourseInfo(uri, id, authHeader);

    }

    @Override
    public PreExamObligation createPreExamObligation(String authHeader, Long id, PreExamObligationDto preExamObligationDto) {

        String uri = "/preExamObligation/course/" + id;
        return gradeManagementServiceClient.createPreExamObligation(uri, authHeader, preExamObligationDto);

    }

    @Override
    public PreExamObligationScore createPreExamObligationScore(String authHeader, Long id, PreExamObligationScoreDto preExamObligationScoreDto) {

        String uri = "/preExamObligationScore/course/" + id;
        return gradeManagementServiceClient.createPreExamObligationScore(uri, authHeader, preExamObligationScoreDto);

    }

    @Override
    public Grade createGrade(String authHeader, Long id, AddGradeDto addGradeDto) {

        String uri = "/grade/course/" + id;
        return gradeManagementServiceClient.createGrade(uri, authHeader, addGradeDto);

    }


}
