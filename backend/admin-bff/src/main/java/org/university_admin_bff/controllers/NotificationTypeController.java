package org.university_admin_bff.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.university_admin_bff.dtos.NotificationTypeDto;
import org.university_admin_bff.services.NotificationsService;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/notificationTypes")
public class NotificationTypeController {

    private NotificationsService notificationsService;

    @Autowired
    public NotificationTypeController(NotificationsService notificationsService) {
        this.notificationsService = notificationsService;
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<NotificationTypeDto>> getAllNotificationTypes(@RequestHeader("Authorization") String authHeader) {
        return ResponseEntity.ok(notificationsService.getAllNotificationTypes(authHeader));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NotificationTypeDto> createNotificationType(@RequestHeader("Authorization") String authHeader, @Valid @RequestBody NotificationTypeDto notificationTypeDto) {
        return ResponseEntity.ok(notificationsService.createNotificationType(authHeader, notificationTypeDto));
    }



    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NotificationTypeDto> updateNotificationTypeById(@PathVariable("id") Long id, @RequestHeader("Authorization") String authHeader, @Valid @RequestBody NotificationTypeDto notificationTypeDto) {
        return ResponseEntity.ok(notificationsService.updateNotificationType(id, authHeader, notificationTypeDto));
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteNotificationTypeById(@PathVariable("id") Long id, @RequestHeader("Authorization") String authHeader) {

        notificationsService.deleteNotificationTypeById(id, authHeader);
        return ResponseEntity.ok().build();

    }





}
