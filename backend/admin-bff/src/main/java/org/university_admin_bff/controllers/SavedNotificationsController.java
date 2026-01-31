package org.university_admin_bff.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.university_admin_bff.dtos.SavedNotification;
import org.university_admin_bff.services.NotificationsService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/savedNotifications")
public class SavedNotificationsController {

    private NotificationsService notificationsService;

    public SavedNotificationsController(NotificationsService notificationsService) {
        this.notificationsService = notificationsService;
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SavedNotification>> searchSavedNotifications(@RequestHeader("Authorization") String authHeader, HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(notificationsService.searchSavedNotifications(authHeader, httpServletRequest));
    }





}
