package org.university_student_client_app.controllers;

import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.university_student_client_app.dto.CourseGeneralInfoData;
import org.university_student_client_app.dto.RegisterExamDto;
import org.university_student_client_app.dto.SingleMessageDto;
import org.university_student_client_app.dto.StudentGradeScoreDto;
import org.university_student_client_app.services.GradeManagementService;
import org.university_student_client_app.utils.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GradeManagementController {

    private GradeManagementService gradeManagementService;
    private JwtUtil jwtUtil;

    @Autowired
    public GradeManagementController(JwtUtil jwtUtil, GradeManagementService gradeManagementService) {
        this.jwtUtil = jwtUtil;
        this.gradeManagementService = gradeManagementService;
    }

    @PreAuthorize("hasAuthority('STUDENT')")
    @GetMapping("/registerExam")
    public String registerExam(Model model, HttpServletRequest httpServletRequest) {

        model.addAttribute("error", null);

        List<CourseGeneralInfoData> coursesList = new ArrayList<>();
        List<RegisterExamDto> registerExams = new ArrayList<>();
        String jwt = jwtUtil.extractJwtFromCookie(httpServletRequest);

        try {

            coursesList = gradeManagementService.getEnrolledCourses(jwt);
            registerExams = gradeManagementService.getRegisteredExams(jwt);

        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
        }

        model.addAttribute("registerExamDto", new RegisterExamDto());
        model.addAttribute("enrolledCourses", coursesList);
        model.addAttribute("registeredExams", registerExams);

        return "registerExam";

    }

    @PreAuthorize("hasAuthority('STUDENT')")
    @PostMapping("/registerExam")
    public String registerExam(@ModelAttribute("registerExamDto") RegisterExamDto registerExamDto, Model model, HttpServletRequest httpServletRequest) {

        model.addAttribute("error", null);

        List<CourseGeneralInfoData> coursesList = new ArrayList<>();
        List<RegisterExamDto> registerExams = new ArrayList<>();
        String jwt = jwtUtil.extractJwtFromCookie(httpServletRequest);

        try {

            coursesList = gradeManagementService.getEnrolledCourses(jwt);
            SingleMessageDto singleMessageDto = gradeManagementService.registerExam(jwt, registerExamDto);
            model.addAttribute("successMessage", singleMessageDto.getMessage());

        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
        }

        try {
            registerExams = gradeManagementService.getRegisteredExams(jwt);
        } catch (RuntimeException e) {
            System.out.println("Error fetching the registered exams!");
        }



        model.addAttribute("registerExamDto", new RegisterExamDto());
        model.addAttribute("enrolledCourses", coursesList);
        model.addAttribute("registeredExams", registerExams);

        return "registerExam";

    }


    @PreAuthorize("hasAuthority('STUDENT')")
    @GetMapping("/grades")
    public String grades(Model model, HttpServletRequest httpServletRequest) {

        model.addAttribute("error", null);

        List<StudentGradeScoreDto> studentGradeScoreDtos = new ArrayList<>();
        String jwt = jwtUtil.extractJwtFromCookie(httpServletRequest);

        try {

            studentGradeScoreDtos = gradeManagementService.getStudentScored(jwt);

        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
        }

        model.addAttribute("studentGradeScoreDtos", studentGradeScoreDtos);

        return "grades";


    }



}

