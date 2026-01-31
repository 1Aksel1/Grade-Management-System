package org.university_student_bff.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.university_student_bff.dtos.CourseGeneralInfoData;
import org.university_student_bff.dtos.RegisterExamDto;
import org.university_student_bff.dtos.SingleMessageDto;
import org.university_student_bff.dtos.StudentGradeScoreDto;
import org.university_student_bff.services.GradeManagementService;
import org.university_student_bff.services.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
public class GradeManagementController {

    private UserService userService;
    private GradeManagementService gradeManagementService;

    @Autowired
    public GradeManagementController(UserService userService, GradeManagementService gradeManagementService) {
        this.userService = userService;
        this.gradeManagementService = gradeManagementService;
    }

    @PreAuthorize("hasAuthority('STUDENT')")
    @GetMapping(path = "/getAllRegisteredExams")
    public ResponseEntity<List<RegisterExamDto>> getAllRegisteredExams(@RequestHeader("Authorization") String authHeader) {
        return ResponseEntity.ok(userService.getRegisteredExams(authHeader));
    }


    @PreAuthorize("hasAuthority('STUDENT')")
    @GetMapping(path = "/courses/studentEnrolled",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CourseGeneralInfoData>> getAllStudentCourses(@RequestHeader("Authorization") String authHeader) {
        return ResponseEntity.ok(gradeManagementService.getEnrolledCourses(authHeader));
    }


    @PreAuthorize("hasAuthority('STUDENT')")
    @PostMapping(path = "/registerExam", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SingleMessageDto> registerExam(@RequestHeader("Authorization") String authHeader, @Valid @RequestBody RegisterExamDto registerExamDto) {
        return ResponseEntity.ok(gradeManagementService.registerExam(authHeader, registerExamDto));
    }


    @PreAuthorize("hasAuthority('STUDENT')")
    @GetMapping(path = "/grade", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentGradeScoreDto>> getGrades(@RequestHeader("Authorization") String authHeader) {
        return ResponseEntity.ok(gradeManagementService.getStudentScores(authHeader));
    }


}
