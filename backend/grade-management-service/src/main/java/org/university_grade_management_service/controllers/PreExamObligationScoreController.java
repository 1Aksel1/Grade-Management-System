package org.university_grade_management_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.university_grade_management_service.dtos.PreExamObligationScoreDto;
import org.university_grade_management_service.dtos.SingleMessageDto;
import org.university_grade_management_service.model.PreExamObligationScore;
import org.university_grade_management_service.services.PreExamObligationScoreService;

import javax.validation.Valid;

@RestController
@RequestMapping("/preExamObligationScore")
public class PreExamObligationScoreController {

    private PreExamObligationScoreService preExamObligationScoreService;

    @Autowired
    public PreExamObligationScoreController(PreExamObligationScoreService preExamObligationScoreService) {
        this.preExamObligationScoreService = preExamObligationScoreService;
    }


    @PreAuthorize("hasAuthority('PROFESSOR')")
    @PostMapping(path = "/course/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PreExamObligationScore> addPreExamObligationScore(@RequestHeader("Authorization") String authHeader, @PathVariable("id") Long courseId, @Valid @RequestBody PreExamObligationScoreDto preExamObligationScoreDto) {
        return ResponseEntity.ok(preExamObligationScoreService.addPreExamObligationScore(authHeader, courseId, preExamObligationScoreDto));
    }





}
