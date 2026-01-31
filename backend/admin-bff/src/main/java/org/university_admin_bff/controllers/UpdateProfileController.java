package org.university_admin_bff.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.university_admin_bff.dtos.*;
import org.university_admin_bff.services.UserService;
import org.university_admin_bff.utils.JwtUtil;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/profile")
public class UpdateProfileController {


    private UserService userService;

    @Autowired
    public UpdateProfileController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UpdateAdminDto> getAdminForUpdate(@RequestHeader("Authorization") String authHeader){
        return ResponseEntity.ok(userService.getAdminUpdateDto(authHeader));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UpdateAdminDto> updateAdmin(@RequestHeader("Authorization") String authHeader, @Valid @RequestBody UpdateAdminDto updateAdminDto) {
        return ResponseEntity.ok(userService.updateAdmin(authHeader, updateAdminDto));
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping(path = "/changeEmail", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponseDto> updateAdminEmail(@RequestHeader("Authorization") String authHeader, @Valid @RequestBody UpdateEmailDto updateEmailDto) {
        return ResponseEntity.ok(userService.updateEmail(authHeader, updateEmailDto));
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(path = "/changePassword", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SingleMessageDto> changePasswordRequest(@RequestHeader("Authorization") String authHeader, @Valid @RequestBody ChangePasswordDto changePasswordDto) {
        return ResponseEntity.ok(userService.updatePassword(authHeader, changePasswordDto));
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(path = "/confirmPasswordChange", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SingleMessageDto> confirmPasswordChange(@RequestHeader("Authorization") String authHeader,
                                                                  @RequestParam("token") String activationLink) {
        userService.confirmPasswordChange(authHeader, activationLink);
        return ResponseEntity.ok(new SingleMessageDto("You have successfully changed your password!"));
    }


}
