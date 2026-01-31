package org.university_student_bff.controllers;

import com.ctc.wstx.shaded.msv_core.util.Uri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.university_student_bff.dtos.*;
import org.university_student_bff.services.UserService;

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

    @PreAuthorize("hasAuthority('STUDENT')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UpdateStudentDto> getStudentForUpdate(@RequestHeader("Authorization") String authHeader) {
        return ResponseEntity.ok(userService.getUpdateStudent(authHeader));
    }

    @PreAuthorize("hasAuthority('STUDENT')")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UpdateStudentDto> updateStudent(@RequestHeader("Authorization") String authHeader, @Valid @RequestBody UpdateStudentDto updateStudentDto) {
        return ResponseEntity.ok(userService.updateStudent(authHeader, updateStudentDto));
    }

    @PreAuthorize("hasAuthority('STUDENT')")
    @PutMapping(path = "/changeEmail", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponseDto> updateStudentEmail(@RequestHeader("Authorization") String authHeader, @Valid @RequestBody UpdateEmailDto updateEmailDto) {
        return ResponseEntity.ok(userService.updateEmail(authHeader, updateEmailDto));
    }

    @PreAuthorize("hasAuthority('STUDENT')")
    @PostMapping(path = "/changePassword", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SingleMessageDto> changePasswordRequest(@RequestHeader("Authorization") String authHeader, @Valid @RequestBody ChangePasswordDto changePasswordDto) {
        return ResponseEntity.ok(userService.updatePassword(authHeader, changePasswordDto));
    }


    @GetMapping(path = "/confirmPasswordChange", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> confirmPasswordChange(@RequestParam("token") String activationLink) {

        userService.confirmPasswordChange(activationLink);

        URI redirectUri = URI.create("http://localhost:8085/confirmPasswordChange");

        return ResponseEntity.status(HttpStatus.PERMANENT_REDIRECT)
                .location(redirectUri)
                .build();

    }




}
