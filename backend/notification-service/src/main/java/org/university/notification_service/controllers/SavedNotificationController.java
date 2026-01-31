package org.university.notification_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.university.notification_service.model.SavedNotification;
import org.university.notification_service.services.SavedNotificationService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/savedNotifications")
public class SavedNotificationController {

    private SavedNotificationService savedNotificationService;

    @Autowired
    public SavedNotificationController(SavedNotificationService savedNotificationService) {
        this.savedNotificationService = savedNotificationService;
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(path = "/admin", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SavedNotification>> findAllSavedNotificationsForAdmin(@RequestParam(value = "email", defaultValue = "") String emailQuery,
                                                                                     @RequestParam(value = "activation_email", defaultValue = "false") boolean activationEmail,
                                                                                     @RequestParam(value = "password_change", defaultValue = "false") boolean passwordChange,
                                                                                     @RequestParam(value = "exam_period_activated", defaultValue = "false") boolean examPeriodActivated,
                                                                                     @RequestParam(value = "grade_added", defaultValue = "false") boolean gradeAdded,
                                                                                     @RequestParam(value = "fromDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromDate,
                                                                                     @RequestParam(value = "toDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toDate) {

        return ResponseEntity.ok(savedNotificationService.findAllSavedNotificationsForAdmin(emailQuery, activationEmail, passwordChange, examPeriodActivated, gradeAdded, fromDate, toDate));

    }

    @PreAuthorize("hasAuthority('STUDENT')")
    @GetMapping(path = "/student", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SavedNotification>> findAllSavedNotificationsForStudent(@RequestHeader("Authorization") String authHeader,
                                                                                       @RequestParam(value = "activation_email", defaultValue = "false") boolean activationEmail,
                                                                                       @RequestParam(value = "password_change", defaultValue = "false") boolean passwordChange,
                                                                                       @RequestParam(value = "exam_period_activated", defaultValue = "false") boolean examPeriodActivated,
                                                                                       @RequestParam(value = "grade_added", defaultValue = "false") boolean gradeAdded,
                                                                                       @RequestParam(value = "fromDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromDate,
                                                                                       @RequestParam(value = "toDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toDate) {

        return ResponseEntity.ok(savedNotificationService.findAllSavedNotificationsForStudent(authHeader, activationEmail, passwordChange, examPeriodActivated, gradeAdded, fromDate, toDate));

    }


    @PreAuthorize("hasAuthority('PROFESSOR')")
    @GetMapping(path = "/professor", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SavedNotification>> findAllSavedNotificationsForProfessor(@RequestHeader("Authorization") String authHeader,
                                                                                         @RequestParam(value = "activation_email", defaultValue = "false") boolean activationEmail,
                                                                                         @RequestParam(value = "password_change", defaultValue = "false") boolean passwordChange,
                                                                                         @RequestParam(value = "exam_period_activated", defaultValue = "false") boolean examPeriodActivated,
                                                                                         @RequestParam(value = "grade_added", defaultValue = "false") boolean gradeAdded,
                                                                                         @RequestParam(value = "fromDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromDate,
                                                                                         @RequestParam(value = "toDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toDate) {


        return ResponseEntity.ok(savedNotificationService.findAllSavedNotificationsForProfessor(authHeader, activationEmail, passwordChange, examPeriodActivated, gradeAdded, fromDate, toDate));

    }




}
