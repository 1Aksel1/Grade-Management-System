package org.university_grade_management_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.university_grade_management_service.dtos.PreExamObligationDto;
import org.university_grade_management_service.dtos.SingleMessageDto;
import org.university_grade_management_service.model.PreExamObligation;
import org.university_grade_management_service.services.PreExamObligationService;

import javax.validation.Valid;

@RestController
@RequestMapping("/preExamObligation")
public class PreExamObligationController {

    private PreExamObligationService preExamObligationService;

    @Autowired
    public PreExamObligationController(PreExamObligationService preExamObligationService) {
        this.preExamObligationService = preExamObligationService;
    }

    @PreAuthorize("hasAuthority('PROFESSOR')")
    @PostMapping(path = "/course/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PreExamObligation> addPreExamObligation(@RequestHeader("Authorization") String authHeader, @PathVariable("id") Long courseId, @Valid @RequestBody PreExamObligationDto preExamObligationDto) {
        return ResponseEntity.ok(preExamObligationService.addPreExamObligation(authHeader, courseId, preExamObligationDto));
    }



}
