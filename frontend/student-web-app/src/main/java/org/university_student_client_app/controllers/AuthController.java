package org.university_student_client_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.university_student_client_app.dto.LoginRequestDto;
import org.university_student_client_app.dto.LoginResponseDto;
import org.university_student_client_app.dto.RegisterStudentDto;
import org.university_student_client_app.dto.SingleMessageDto;
import org.university_student_client_app.exceptions.WrongCredentialsException;
import org.university_student_client_app.services.AuthService;
import org.university_student_client_app.utils.JwtUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class AuthController {

    private AuthService authService;
    private JwtUtil jwtUtil;

    @Autowired
    public AuthController(AuthService authService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }


    @GetMapping("/login")
    public String login(Model model, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        if(jwtUtil.isStudentJwtPresent(httpServletRequest, httpServletResponse)) {
            return "/home";
        }

        model.addAttribute("loginRequest", new LoginRequestDto());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("loginRequest") LoginRequestDto loginRequestDto, Model model, HttpServletResponse httpServletResponse) {

        try {

            LoginResponseDto loginResponseDto = authService.login(loginRequestDto);

            Cookie cookie = new Cookie("jwt", loginResponseDto.getJwt());
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            httpServletResponse.addCookie(cookie);

            return "home";

        } catch (RuntimeException e) {
            model.addAttribute("error", "Wrong credentials!");
        }

        return "login";

    }

    @GetMapping("/register")
    public String register(Model model, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        if(jwtUtil.isStudentJwtPresent(httpServletRequest, httpServletResponse)) {
            return "/home";
        }

        model.addAttribute("registerStudentDto", new RegisterStudentDto());

        return "register";
    }


    @GetMapping("/confirmRegistration")
    public String confirmRegistration(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        if(jwtUtil.isStudentJwtPresent(httpServletRequest, httpServletResponse)) {
            return "/home";
        }

        return "confirmRegistration";
    }

    @GetMapping("/registrationUnsuccessful")
    public String registrationUnsuccessful(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        System.out.println("pozove li se ovo?");

        if(jwtUtil.isStudentJwtPresent(httpServletRequest, httpServletResponse)) {
            return "/home";
        }

        return "registrationUnsuccessful";
    }


    @PostMapping("/register")
    public String register(@ModelAttribute("registerStudentDto") RegisterStudentDto registerStudentDto, Model model, HttpServletResponse httpServletResponse) {

        model.addAttribute("successMessage", null);
        model.addAttribute("error", null);


        try {

            SingleMessageDto singleMessageDto = authService.register(registerStudentDto, httpServletResponse);
            model.addAttribute("successMessage", singleMessageDto.getMessage());

        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
        }

        return "register";

    }



}

