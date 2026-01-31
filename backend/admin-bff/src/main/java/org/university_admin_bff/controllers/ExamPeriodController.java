package org.university_admin_bff.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.university_admin_bff.dtos.ExamPeriod;
import org.university_admin_bff.dtos.ExamPeriodStatusDto;
import org.university_admin_bff.dtos.SingleMessageDto;
import org.university_admin_bff.services.GradeManagementService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/examPeriod")
public class ExamPeriodController {

    private GradeManagementService gradeManagementService;

    @Autowired
    public ExamPeriodController(GradeManagementService gradeManagementService) {
        this.gradeManagementService = gradeManagementService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ExamPeriod>> findAll(@RequestHeader("Authorization") String authHeader) {
        return ResponseEntity.ok(gradeManagementService.getAllExamPeriods(authHeader));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping(value = "/activate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SingleMessageDto> activateExamPeriod(@RequestHeader("Authorization") String authHeader,
                                                               @Valid @RequestBody ExamPeriodStatusDto examPeriodStatusDto) {
        return ResponseEntity.ok(gradeManagementService.activateExamPeriod(authHeader, examPeriodStatusDto));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping(value = "/deactivate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SingleMessageDto> deactivateExamPeriod(@RequestHeader("Authorization") String authHeader,
                                                                 @Valid @RequestBody ExamPeriodStatusDto examPeriodStatusDto) {
        return ResponseEntity.ok(gradeManagementService.deactivateExamPeriod(authHeader, examPeriodStatusDto));
    }

}
