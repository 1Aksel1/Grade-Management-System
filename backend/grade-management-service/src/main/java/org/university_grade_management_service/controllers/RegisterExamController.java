package org.university_grade_management_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.university_grade_management_service.dtos.RegisterExamRequestDto;
import org.university_grade_management_service.dtos.SingleMessageDto;
import org.university_grade_management_service.services.RegisterExamService;

import javax.validation.Valid;

@RestController
@RequestMapping("/registerExam")
public class RegisterExamController {

    private RegisterExamService registerExamService;

    @Autowired
    public RegisterExamController(RegisterExamService registerExamService) {
        this.registerExamService = registerExamService;
    }

    @PreAuthorize("hasAuthority('STUDENT')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SingleMessageDto> registerExam(@RequestHeader("Authorization") String authHeader, @Valid @RequestBody RegisterExamRequestDto registerExamDto) {

        registerExamService.registerExam(authHeader, registerExamDto);
        return ResponseEntity.ok(new SingleMessageDto("Successfully registered the exam!"));

    }





}
