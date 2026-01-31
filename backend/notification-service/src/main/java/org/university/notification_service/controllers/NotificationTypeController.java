package org.university.notification_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.university.notification_service.dtos.NotificationTypeDto;
import org.university.notification_service.model.NotificationType;
import org.university.notification_service.services.NotificationTypeService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/notificationTypes")
public class NotificationTypeController {

    private NotificationTypeService notificationTypeService;

    @Autowired
    public NotificationTypeController(NotificationTypeService notificationTypeService) {
        this.notificationTypeService = notificationTypeService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<NotificationTypeDto>> getAllNotificationTypes() {
        return ResponseEntity.ok(notificationTypeService.findAll());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NotificationTypeDto> createNotificationType(@Valid @RequestBody NotificationTypeDto notificationTypeDto) {
        return ResponseEntity.ok(notificationTypeService.createNotificationType(notificationTypeDto));
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NotificationTypeDto> getNotificationTypeById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(notificationTypeService.findById(id));
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NotificationTypeDto> updateNotificationTypeById(@PathVariable("id") Long id, @Valid @RequestBody NotificationTypeDto notificationTypeDto) {
        return ResponseEntity.ok(notificationTypeService.updateNotificationType(id, notificationTypeDto));
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteNotificationTypeById(@PathVariable("id") Long id) {

        notificationTypeService.deleteById(id);
        return ResponseEntity.ok().build();

    }




}
