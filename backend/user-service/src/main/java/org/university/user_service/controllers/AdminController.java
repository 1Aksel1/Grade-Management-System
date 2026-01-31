package org.university.user_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.university.user_service.dtos.*;
import org.university.user_service.services.AdminService;
import org.university.user_service.services.ChangePasswordRequestService;
import org.university.user_service.utils.JwtUtil;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private AdminService adminService;
    private ChangePasswordRequestService changePasswordService;
    private JwtUtil jwtUtil;

    @Autowired
    public AdminController(AdminService adminService, ChangePasswordRequestService changePasswordService, JwtUtil jwtUtil) {
        this.adminService = adminService;
        this.changePasswordService = changePasswordService;
        this.jwtUtil = jwtUtil;
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UpdateAdminDto> getAdminForUpdate(@RequestHeader("Authorization") String authHeader){

        return ResponseEntity.ok(adminService.getAdminForUpdate(authHeader));

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UpdateAdminDto> updateAdmin(@RequestHeader("Authorization") String authHeader, @Valid @RequestBody UpdateAdminDto updateAdminDto) {

        return ResponseEntity.ok(adminService.updateAdmin(authHeader, updateAdminDto));

    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping(path = "/changeEmail", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponseDto> updateAdminEmail(@RequestHeader("Authorization") String authHeader, @Valid @RequestBody UpdateEmailDto updateEmailDto) {

        adminService.changeEmail(authHeader, updateEmailDto);
        return ResponseEntity.ok(new LoginResponseDto(jwtUtil.generateToken(updateEmailDto.getEmail())));

    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(path = "/changePassword", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SingleMessageDto> changePasswordRequest(@RequestHeader("Authorization") String authHeader, @Valid @RequestBody ChangePasswordDto changePasswordDto) {

        adminService.checkPasswordMatch(authHeader, changePasswordDto.getOldPassword());
        changePasswordService.createPasswordChangeRequest(authHeader, changePasswordDto);
        return ResponseEntity.ok(new SingleMessageDto("Please check your email to confirm the process of changing the password!"));

    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(path = "/confirmPasswordChange", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SingleMessageDto> confirmPasswordChange(@RequestParam("token") String activationLink) {

        adminService.changePassword(activationLink);
        return ResponseEntity.ok(new SingleMessageDto("You have successfully changed your password!"));

    }









}
