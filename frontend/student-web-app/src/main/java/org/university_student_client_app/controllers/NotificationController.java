package org.university_student_client_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.university_student_client_app.dto.FilterParametersDto;
import org.university_student_client_app.dto.NotificationDto;
import org.university_student_client_app.services.NotificationService;
import org.university_student_client_app.utils.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class NotificationController {

    private NotificationService notificationService;
    private JwtUtil jwtUtil;

    @Autowired
    public NotificationController(NotificationService notificationService, JwtUtil jwtUtil) {
        this.notificationService = notificationService;
        this.jwtUtil = jwtUtil;
    }

    @PreAuthorize("hasAuthority('STUDENT')")
    @GetMapping("/notifications")
    public String notifications(Model model, HttpServletRequest httpServletRequest) {

        model.addAttribute("error", null);

        FilterParametersDto filterParametersDto = new FilterParametersDto(true, true, true, true);
        List<NotificationDto> notificationDtoList = new ArrayList<>();

        String jwt = jwtUtil.extractJwtFromCookie(httpServletRequest);

        try {

            notificationDtoList = notificationService.getStudentNotifications(jwt, filterParametersDto);

        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
        }


        model.addAttribute("filterParametersDto", filterParametersDto);
        model.addAttribute("notifications", notificationDtoList);


        return "notifications";

    }

    @PreAuthorize("hasAuthority('STUDENT')")
    @PostMapping("/notifications")
    public String notifications(@ModelAttribute("filterParametersDto") FilterParametersDto filterParametersDto, Model model, HttpServletRequest httpServletRequest) {

        model.addAttribute("error", null);

        List<NotificationDto> notificationDtoList = new ArrayList<>();

        String jwt = jwtUtil.extractJwtFromCookie(httpServletRequest);

        try {

            notificationDtoList = notificationService.getStudentNotifications(jwt, filterParametersDto);

        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
        }


        model.addAttribute("notifications", notificationDtoList);

        return "notifications";

    }







}
