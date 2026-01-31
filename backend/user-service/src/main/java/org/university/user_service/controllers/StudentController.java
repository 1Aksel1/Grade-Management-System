package org.university.user_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.university.user_service.dtos.*;
import org.university.user_service.model.UserType;
import org.university.user_service.services.ChangePasswordRequestService;
import org.university.user_service.services.RegistrationService;
import org.university.user_service.services.StudentService;
import org.university.user_service.utils.JwtUtil;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/student")
@Validated
public class StudentController {

    private StudentService studentService;
    private RegistrationService registrationService;
    private ChangePasswordRequestService changePasswordService;
    private JwtUtil jwtUtil;

    @Autowired
    public StudentController(StudentService studentService, RegistrationService registrationService, ChangePasswordRequestService changePasswordService, JwtUtil jwtUtil) {
        this.studentService = studentService;
        this.registrationService = registrationService;
        this.changePasswordService = changePasswordService;
        this.jwtUtil = jwtUtil;
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SingleMessageDto> registerStudent(@Valid @RequestBody CreateStudentDto createStudentDto) {

        studentService.registerStudent(createStudentDto);

        Long userId = studentService.getStudentIdForEmail(createStudentDto.getEmail());
        UserType userType = UserType.STUDENT;
        String sessionId = registrationService.getSessionIdForUserIdAndUserType(userId, userType);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.SET_COOKIE, "JSESSIONID=" + sessionId + "; HttpOnly; Path=/; Max-Age=3600");

        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).body(new SingleMessageDto("Successfully registered! Please check your email to finish the process!"));

    }


    @PreAuthorize("hasAuthority('STUDENT')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UpdateStudentDto> getStudentForUpdate(@RequestHeader("Authorization") String authHeader) {

        return ResponseEntity.ok(studentService.getStudentForUpdate(authHeader));

    }

    @PreAuthorize("hasAuthority('STUDENT')")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UpdateStudentDto> updateStudent(@RequestHeader("Authorization") String authHeader, @Valid @RequestBody UpdateStudentDto updateStudentDto) {

        return ResponseEntity.ok(studentService.updateStudent(authHeader, updateStudentDto));

    }

    @PreAuthorize("hasAuthority('STUDENT')")
    @PutMapping(path = "/changeEmail", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponseDto> updateStudentEmail(@RequestHeader("Authorization") String authHeader, @Valid @RequestBody UpdateEmailDto updateEmailDto) {

        studentService.changeEmail(authHeader, updateEmailDto);
        return ResponseEntity.ok(new LoginResponseDto(jwtUtil.generateToken(updateEmailDto.getEmail())));

    }




    @PreAuthorize("hasAuthority('STUDENT')")
    @PostMapping(path = "/changePassword", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SingleMessageDto> changePasswordRequest(@RequestHeader("Authorization") String authHeader, @Valid @RequestBody ChangePasswordDto changePasswordDto) {

        studentService.checkPasswordMatch(authHeader, changePasswordDto.getOldPassword());
        changePasswordService.createPasswordChangeRequest(authHeader, changePasswordDto);
        return ResponseEntity.ok(new SingleMessageDto("Please check your email to confirm the process of changing the password!"));

    }


    @PreAuthorize("hasAuthority('STUDENT')")
    @PostMapping(path = "/registerExam")
    public ResponseEntity<Boolean> registerExam(@Valid @RequestBody RegisterExamDto registerExamDto) {
        return ResponseEntity.ok(studentService.registerExam(registerExamDto));
    }


    @PreAuthorize("hasAuthority('PROFESSOR')")
    @DeleteMapping(path = "/isExamRegistered")
    public ResponseEntity<Boolean> isExamRegistered(@RequestParam("studentIndex") @NotBlank @Size(min = 5, max = 25) String studentIndex,
                                                    @RequestParam("courseName")  @NotBlank @Size(min = 2, max = 40) String courseName,
                                                    @RequestParam("examPeriod") @NotBlank @Pattern(regexp = "JANUARY|FEBRUARY|JUNE|JULY|AUGUST|SEPTEMBER") String examPeriod) {

        return  ResponseEntity.ok(studentService.isExamRegistered(studentIndex, courseName, examPeriod));

    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(path = "/getAllUsernamesAndEmails")
    public ResponseEntity<List<StudentUsernameAndEmailDto>> getAllStudentUsernamesAndEmails() {
        return ResponseEntity.ok(studentService.findStudentUsernamesAndEmails());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(path = "/getNameAndEmail")
    public ResponseEntity<StudentNameAndEmailDto> getStudentNameAndEmail(@RequestParam("studentIndex") String studentIndex) {
        return ResponseEntity.ok(studentService.findStudentNameAndEmail(studentIndex));
    }

    @PreAuthorize("hasAuthority('STUDENT')")
    @GetMapping(path = "/email")
    public ResponseEntity<String> getStudentEmail(@RequestHeader("Authorization") String authHeader) {
        return ResponseEntity.ok(studentService.getStudentEmail(authHeader));
    }

    @PreAuthorize("hasAuthority('STUDENT')")
    @GetMapping(path = "/getAllRegisteredExams")
    public ResponseEntity<List<RegisterExamDto>> getAllRegisteredExams(@RequestHeader("Authorization") String authHeader) {
        return ResponseEntity.ok(studentService.findAllRegisteredExams(authHeader));
    }



}


