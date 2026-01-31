package org.university_professor_bff.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.university_professor_bff.dtos.SavedNotification;
import org.university_professor_bff.services.NotificationService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/savedNotifications")
public class SavedNotificationsController {

    private NotificationService notificationService;

    @Autowired
    public SavedNotificationsController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PreAuthorize("hasAuthority('PROFESSOR')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SavedNotification>> searchSavedNotifications(@RequestHeader("Authorization") String authHeader, HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(notificationService.searchSavedNotifications(authHeader, httpServletRequest));
    }



}
