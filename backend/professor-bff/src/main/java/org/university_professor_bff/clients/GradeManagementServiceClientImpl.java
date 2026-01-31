package org.university_professor_bff.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.university_professor_bff.dtos.*;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple5;

import java.util.Collections;
import java.util.List;

@Service
public class GradeManagementServiceClientImpl implements GradeManagementServiceClient {

    private WebClient gradeManagementServiceWebClient;
    private WebClientErrorHandler webClientErrorHandler;

    @Autowired
    public GradeManagementServiceClientImpl(WebClient gradeManagementServiceWebClient, WebClientErrorHandler webClientErrorHandler) {
        this.gradeManagementServiceWebClient = gradeManagementServiceWebClient;
        this.webClientErrorHandler = webClientErrorHandler;
    }

    @Override
    public List<CourseGeneralInfoDto> getProfessorCourses(String uri, String authHeader) {

        ClientResponse response = this.gradeManagementServiceWebClient
                .get()
                .uri(uri)
                .header("Authorization", authHeader)
                .exchange().block();

        webClientErrorHandler.handleHttpErrors(response);

        return response.bodyToFlux(CourseGeneralInfoDto.class)
                .collectList()
                .block();

    }

    @Override
    public Mono<CourseCompleteInfo> getCompleteCourseInfo(String uri, Long id, String authHeader) {

        Mono<CourseGeneralInfoDto> courseGeneralInfoData = fetchCourseGeneralInfo(uri, id, authHeader);
        Mono<List<PreExamObligation>> preExamObligationsData = fetchPreExamObligations(uri, id, authHeader);
        Mono<List<PreExamObligationScore>> preExamObligationScoreData = fetchPreExamObligationScores(uri, id, authHeader);
        Mono<List<StudentEnrollment>> studentEnrollmentsData = fetchStudentEnrollments(uri, id, authHeader);
        Mono<List<Grade>> gradesData = fetchGrades(uri, id, authHeader);

        return Mono.zip(
                        courseGeneralInfoData,
                        preExamObligationsData,
                        preExamObligationScoreData,
                        studentEnrollmentsData,
                        gradesData
                )
                .map(this::combine);
    }

    private CourseCompleteInfo combine(Tuple5<CourseGeneralInfoDto, List<PreExamObligation>, List<PreExamObligationScore>, List<StudentEnrollment>, List<Grade>> tuple) {
        return new CourseCompleteInfo(tuple.getT1(), tuple.getT2(), tuple.getT3(), tuple.getT4(), tuple.getT5());
    }

    private Mono<CourseGeneralInfoDto> fetchCourseGeneralInfo(String uri, Long id, String authHeader) {
        return gradeManagementServiceWebClient
                .get()
                .uri(uri + "generalInfo/" + id)
                .header("Authorization", authHeader)
                .retrieve()
                .bodyToMono(CourseGeneralInfoDto.class)
                .doOnNext(r -> System.out.println("\"✅ GeneralInfo fetched"))
                .doOnError(e -> System.out.println("\"❌ GeneralInfo FAILED\""));
                // .onErrorReturn(new CourseGeneralInfoDto());
    }

    private Mono<List<PreExamObligation>> fetchPreExamObligations(String uri, Long id, String authHeader) {
        return gradeManagementServiceWebClient
                .get()
                .uri(uri + id + "/preExamObligations")
                .header("Authorization", authHeader)
                .retrieve()
                .bodyToFlux(PreExamObligation.class)
                .collectList()
                .doOnNext(r -> System.out.println("\"✅ Pre exam obligations fetched"))
                .doOnError(e -> System.out.println("\"❌ Pre exam obligations FAILED\""));
                //.onErrorReturn(Collections.emptyList());
    }

    private Mono<List<PreExamObligationScore>> fetchPreExamObligationScores(String uri, Long id, String authHeader) {
        return gradeManagementServiceWebClient
                .get()
                .uri(uri + id + "/preExamObligationsScores")
                .header("Authorization", authHeader)
                .retrieve()
                .bodyToFlux(PreExamObligationScore.class)
                .collectList()
                .doOnNext(r -> System.out.println("\"✅ Pre exam obligation scores fetched"))
                .doOnError(e -> System.out.println("\"❌ Pre exam obligation scores FAILED\""));
        //.onErrorReturn(Collections.emptyList());

    }

    private Mono<List<StudentEnrollment>> fetchStudentEnrollments(String uri, Long id, String authHeader) {
        return gradeManagementServiceWebClient
                .get()
                .uri(uri + id + "/studentEnrollments")
                .header("Authorization", authHeader)
                .retrieve()
                .bodyToFlux(StudentEnrollment.class)
                .collectList()
                .doOnNext(r -> System.out.println("\"✅ StudentEnrollments fetched"))
                .doOnError(e -> System.out.println("\"❌ Student Enrollemnets FAILED\""));
        //.onErrorReturn(Collections.emptyList());


    }

    private Mono<List<Grade>> fetchGrades(String uri, Long id, String authHeader) {
        return gradeManagementServiceWebClient
                .get()
                .uri(uri + id + "/grades")
                .header("Authorization", authHeader)
                .retrieve()
                .bodyToFlux(Grade.class)
                .collectList()
                .doOnNext(r -> System.out.println("\"✅ Fetch Grades fetched"))
                .doOnError(e -> System.out.println("\"❌ Fetch Grades FAILED\""));
        //.onErrorReturn(Collections.emptyList());
    }

    @Override
    public PreExamObligation createPreExamObligation(String uri, String authHeader, PreExamObligationDto preExamObligationDto) {

        ClientResponse response = this.gradeManagementServiceWebClient
                .post()
                .uri(uri)
                .header("Authorization", authHeader)
                .header("Content-Type", "application/json")
                .bodyValue(preExamObligationDto)
                .exchange().block();

        webClientErrorHandler.handleHttpErrors(response);

        return response.bodyToMono(PreExamObligation.class).block();

    }

    @Override
    public PreExamObligationScore createPreExamObligationScore(String uri, String authHeader, PreExamObligationScoreDto preExamObligationScoreDto) {

        ClientResponse response = this.gradeManagementServiceWebClient
                .post()
                .uri(uri)
                .header("Authorization", authHeader)
                .header("Content-Type", "application/json")
                .bodyValue(preExamObligationScoreDto)
                .exchange().block();

        webClientErrorHandler.handleHttpErrors(response);

        return response.bodyToMono(PreExamObligationScore.class).block();

    }


    @Override
    public Grade createGrade(String uri, String authHeader, AddGradeDto addGradeDto) {

        ClientResponse response = this.gradeManagementServiceWebClient
                .post()
                .uri(uri)
                .header("Authorization", authHeader)
                .header("Content-Type", "application/json")
                .bodyValue(addGradeDto)
                .exchange().block();

        webClientErrorHandler.handleHttpErrors(response);

        return response.bodyToMono(Grade.class).block();

    }

}
