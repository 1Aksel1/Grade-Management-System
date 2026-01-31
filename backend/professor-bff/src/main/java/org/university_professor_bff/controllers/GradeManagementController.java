package org.university_professor_bff.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.university_professor_bff.dtos.*;
import org.university_professor_bff.services.GradeManagementService;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
public class GradeManagementController {

    private GradeManagementService gradeManagementService;

    public GradeManagementController(GradeManagementService gradeManagementService) {
        this.gradeManagementService = gradeManagementService;
    }

    @PreAuthorize("hasAuthority('PROFESSOR')")
    @GetMapping(path = "/professorCourses", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CourseGeneralInfoDto>> getAllProfessorCourses(@RequestHeader("Authorization") String authHeader) {
        return ResponseEntity.ok(gradeManagementService.getProfessorCourses(authHeader));
    }

    @PreAuthorize("hasAuthority('PROFESSOR')")
    @GetMapping(path = "/courses/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<CourseCompleteInfo>> getCourseForId(@RequestHeader("Authorization") String authHeader, @PathVariable("id") Long id) {
        return gradeManagementService.getSingleCourse(authHeader, id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAuthority('PROFESSOR')")
    @PostMapping(path = "preExamObligation/course/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PreExamObligation> addPreExamObligation(@RequestHeader("Authorization") String authHeader, @PathVariable("id") Long id, @Valid @RequestBody PreExamObligationDto preExamObligationDto) {
        return ResponseEntity.ok(gradeManagementService.createPreExamObligation(authHeader, id, preExamObligationDto));
    }

    @PreAuthorize("hasAuthority('PROFESSOR')")
    @PostMapping(path = "/preExamObligationScore/course/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PreExamObligationScore> addPreExamObligationScore(@RequestHeader("Authorization") String authHeader, @PathVariable("id") Long id, @Valid @RequestBody PreExamObligationScoreDto preExamObligationScoreDto) {
        return ResponseEntity.ok(gradeManagementService.createPreExamObligationScore(authHeader, id, preExamObligationScoreDto));
    }

    @PreAuthorize("hasAuthority('PROFESSOR')")
    @PostMapping(path = "/grade/course/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Grade> addGrade(@RequestHeader("Authorization") String authHeader, @PathVariable("id") Long id, @Valid @RequestBody AddGradeDto gradeDto) {
        return ResponseEntity.ok(gradeManagementService.createGrade(authHeader, id, gradeDto));
    }



}
