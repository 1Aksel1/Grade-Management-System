package org.university_grade_management_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.university_grade_management_service.dtos.AddGradeDto;
import org.university_grade_management_service.dtos.SingleMessageDto;
import org.university_grade_management_service.dtos.StudentGradeScoreDto;
import org.university_grade_management_service.model.Grade;
import org.university_grade_management_service.services.GradeService;
import org.university_grade_management_service.services.PreExamObligationScoreService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/grade")
public class GradeController {

    private GradeService gradeService;

    @Autowired
    public GradeController(GradeService gradeService, PreExamObligationScoreService preExamObligationScoreService) {
        this.gradeService = gradeService;
    }


    @PreAuthorize("hasAuthority('PROFESSOR')")
    @PostMapping(path = "/course/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Grade> addGrade(@RequestHeader("Authorization") String authHeader, @PathVariable("id") Long courseId, @Valid @RequestBody AddGradeDto gradeDto) {
        return ResponseEntity.ok(gradeService.addGrade(authHeader, courseId, gradeDto));
    }

    @PreAuthorize("hasAuthority('STUDENT')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentGradeScoreDto>> getGrades(@RequestHeader("Authorization") String authHeader) {

        return ResponseEntity.ok(gradeService.getGrades(authHeader));

    }



}
