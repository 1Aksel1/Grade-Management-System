package org.university_professor_bff.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.university_professor_bff.dtos.*;
import org.university_professor_bff.services.UserService;

import javax.validation.Valid;
import java.net.URI;

@RestController
@CrossOrigin
@RequestMapping("/profile")
public class UpdateProfileController {

    private UserService userService;

    @Autowired
    public UpdateProfileController(UserService userService) {
        this.userService = userService;
    }


    @PreAuthorize("hasAuthority('PROFESSOR')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UpdateProfessorDto> getProfessorForUpdate(@RequestHeader("Authorization") String authHeader) {
        return ResponseEntity.ok(userService.getUpdateProfessor(authHeader));
    }

    @PreAuthorize("hasAuthority('PROFESSOR')")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UpdateProfessorDto> updateProfessor(@RequestHeader("Authorization") String authHeader, @Valid @RequestBody UpdateProfessorDto updateProfessorDto) {
        return ResponseEntity.ok(userService.updateProfessor(authHeader, updateProfessorDto));
    }

    @PreAuthorize("hasAuthority('PROFESSOR')")
    @PutMapping(path = "/changeEmail", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponseDto> updateProfessorEmail(@RequestHeader("Authorization") String authHeader, @Valid @RequestBody UpdateEmailDto updateEmailDto) {
        return ResponseEntity.ok(userService.updateEmail(authHeader, updateEmailDto));
    }

    @PreAuthorize("hasAuthority('PROFESSOR')")
    @PostMapping(path = "/changePassword", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SingleMessageDto> changePasswordRequest(@RequestHeader("Authorization") String authHeader, @Valid @RequestBody ChangePasswordDto changePasswordDto) {
        return ResponseEntity.ok(userService.updatePassword(authHeader, changePasswordDto));
    }

    @GetMapping(path = "/confirmPasswordChange", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> confirmPasswordChange(@RequestParam("token") String activationLink) {

        userService.confirmPasswordChange(activationLink);

        URI redirectUri = URI.create("http://localhost:4200/confirmPasswordChange");

        return ResponseEntity.status(HttpStatus.PERMANENT_REDIRECT)
                .location(redirectUri)
                .build();

    }




}
