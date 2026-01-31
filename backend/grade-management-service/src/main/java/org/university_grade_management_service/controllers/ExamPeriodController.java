package org.university_grade_management_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.university_grade_management_service.dtos.ExamPeriodDto;
import org.university_grade_management_service.dtos.SingleMessageDto;
import org.university_grade_management_service.model.ExamPeriod;
import org.university_grade_management_service.model.Period;
import org.university_grade_management_service.services.ExamPeriodService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/examPeriod")
public class ExamPeriodController {


    private ExamPeriodService examPeriodService;

    @Autowired
    public ExamPeriodController(ExamPeriodService examPeriodService) {
        this.examPeriodService = examPeriodService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping(value = "/activate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SingleMessageDto> activateExamPeriod(@Valid @RequestBody ExamPeriodDto examPeriodDto) {

        String msg = examPeriodService.activateExamPeriod(Period.valueOf(examPeriodDto.getExamPeriod()));
        return ResponseEntity.ok(new SingleMessageDto(msg));

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping(value = "/deactivate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SingleMessageDto> deactivateExamPeriod(@Valid @RequestBody ExamPeriodDto examPeriodDto) {

        String msg = examPeriodService.deactivateExamPeriod(Period.valueOf(examPeriodDto.getExamPeriod()));
        return ResponseEntity.ok(new SingleMessageDto(msg));

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ExamPeriod>> findAll() {
        return ResponseEntity.ok(examPeriodService.findAll());
    }




}
