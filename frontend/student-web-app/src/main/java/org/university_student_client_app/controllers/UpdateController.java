package org.university_student_client_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.university_student_client_app.dto.*;
import org.university_student_client_app.services.UpdateService;
import org.university_student_client_app.utils.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UpdateController {

    private UpdateService updateService;
    private JwtUtil jwtUtil;

    @Autowired
    public UpdateController(UpdateService updateService, JwtUtil jwtUtil) {
        this.updateService = updateService;
        this.jwtUtil = jwtUtil;
    }

    @PreAuthorize("hasAuthority('STUDENT')")
    @GetMapping("/updateStudent")
    public String updateStudent(Model model, HttpServletRequest httpServletRequest) {

        String jwt = jwtUtil.extractJwtFromCookie(httpServletRequest);

        try {

            UpdateStudentDto updateStudentDto = updateService.getUpdateStudentDto(jwt);
            model.addAttribute("updateStudentDto", updateStudentDto);


        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
        }



        return "updateStudent";
    }

    @PreAuthorize("hasAuthority('STUDENT')")
    @GetMapping("/updateEmail")
    public String updateEmail(Model model) {

        model.addAttribute("updateEmailDto", new UpdateEmailDto());
        return "updateEmail";

    }

    @PreAuthorize("hasAuthority('STUDENT')")
    @GetMapping("/updatePassword")
    public String updatePassword(Model model) {

        model.addAttribute("changePasswordDto", new ChangePasswordDto());
        return "updatePassword";

    }

    @PreAuthorize("hasAuthority('STUDENT')")
    @GetMapping("/confirmPasswordChange")
    public String confirmPasswordChange() {
        return "confirmPasswordChange";
    }

    @PreAuthorize("hasAuthority('STUDENT')")
    @GetMapping("/passwordChangeUnsuccessful")
    public String passwordChangeUnsuccessful() {
        return "passwordChangeUnsuccessful";
    }


    @PreAuthorize("hasAuthority('STUDENT')")
    @PostMapping("/updatePassword")
    public String updatePassword(@ModelAttribute("changePasswordDto") ChangePasswordDto changePasswordDto, Model model, HttpServletRequest httpServletRequest) {

        model.addAttribute("error", null);
        model.addAttribute("successMessage", null);

        String jwt = jwtUtil.extractJwtFromCookie(httpServletRequest);

        try {

            SingleMessageDto singleMessageDto = updateService.updatePassword(jwt, changePasswordDto);
            model.addAttribute("successMessage", singleMessageDto.getMessage());

        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
        }

        return "updatePassword";

    }




    @PreAuthorize("hasAuthority('STUDENT')")
    @PostMapping("/updateEmail")
    public String updateEmail(@ModelAttribute("updateEmailDto") UpdateEmailDto updateEmailDto, Model model, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        model.addAttribute("error", null);
        model.addAttribute("successMessage", null);

        String jwt = jwtUtil.extractJwtFromCookie(httpServletRequest);

        try {

            LoginResponseDto loginResponseDto = updateService.updateEmail(jwt, updateEmailDto);
            jwtUtil.refreshJwt(loginResponseDto.getJwt(), httpServletRequest, httpServletResponse);
            model.addAttribute("successMessage", "Successfully updated the email!");


        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
        }

        return "updateEmail";

    }


    @PreAuthorize("hasAuthority('STUDENT')")
    @PostMapping("/updateStudent")
    public String updateStudent(@ModelAttribute("updateStudentDto") UpdateStudentDto updateStudentDto, Model model, HttpServletRequest httpServletRequest) {

        model.addAttribute("error", null);
        model.addAttribute("successMessage", null);

        String jwt = jwtUtil.extractJwtFromCookie(httpServletRequest);

        try {

            UpdateStudentDto updatedStudent = updateService.updateStudent(jwt, updateStudentDto);
            model.addAttribute("updateStudentDto", updatedStudent);
            model.addAttribute("successMessage", "Successfully updated the profile!");


        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
        }


        return "updateStudent";

    }



}
