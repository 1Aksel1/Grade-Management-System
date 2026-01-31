package org.university_grade_management_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.university_grade_management_service.dtos.CourseGeneralInfoDto;
import org.university_grade_management_service.model.*;
import org.university_grade_management_service.services.CourseService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/courses")
public class CourseController {

    private CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PreAuthorize("hasAuthority('PROFESSOR')")
    @GetMapping(path = "/generalInfo/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CourseGeneralInfoDto> getCourseGeneralInfoForId(@RequestHeader("Authorization") String authHeader,
                                                                          @PathVariable("id") Long id) {
        return ResponseEntity.ok(courseService.getCourseGeneralInfoForId(authHeader, id));
    }

    @PreAuthorize("hasAuthority('PROFESSOR')")
    @GetMapping(path = "/{id}/preExamObligations", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PreExamObligation>> getPreExamObligationsForCourseId(@RequestHeader("Authorization") String authHeader,
                                                                                    @PathVariable("id") Long id) {
        return ResponseEntity.ok(courseService.getPreExamObligationsForCourseId(authHeader, id));
    }

    @PreAuthorize("hasAuthority('PROFESSOR')")
    @GetMapping(path = "/{id}/preExamObligationsScores", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PreExamObligationScore>> getPreExamObligationsScoresForCourseId(@RequestHeader("Authorization") String authHeader,
                                                                                               @PathVariable("id") Long id) {
        return ResponseEntity.ok(courseService.getPreExamObligationsScoresForCourseId(authHeader, id));
    }

    @PreAuthorize("hasAuthority('PROFESSOR')")
    @GetMapping(path = "/{id}/studentEnrollments", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentEnrollment>> getStudentEnrollmentsForCourseId(@RequestHeader("Authorization") String authHeader,
                                                                                    @PathVariable("id") Long id) {
        return ResponseEntity.ok(courseService.getStudentEnrollmentsForCourseId(authHeader, id));
    }

    @PreAuthorize("hasAuthority('PROFESSOR')")
    @GetMapping(path = "/{id}/grades", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Grade>> getGradesForCourseId(@RequestHeader("Authorization") String authHeader,
                                                            @PathVariable("id") Long id) {
        return ResponseEntity.ok(courseService.getGradesForCourseId(authHeader, id));
    }

    @PreAuthorize("hasAuthority('PROFESSOR')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CourseGeneralInfoDto>> getAllProfessorCourses(@RequestHeader("Authorization") String authHeader) {
        return ResponseEntity.ok(courseService.findAllProfessorCourses(authHeader));
    }

    @PreAuthorize("hasAuthority('STUDENT')")
    @GetMapping(path = "/studentEnrolled",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CourseGeneralInfoDto>> getAllStudentCourses(@RequestHeader("Authorization") String authHeader) {
        return ResponseEntity.ok(courseService.findAllStudentActiveCourses(authHeader));

    }

    @PreAuthorize("hasAuthority('PROFESSOR')")
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Course> getCourseForId(@RequestHeader("Authorization") String authHeader, @PathVariable("id") Long id) {
        return ResponseEntity.ok(courseService.validateProfessorCourse(authHeader, id));
    }


}
