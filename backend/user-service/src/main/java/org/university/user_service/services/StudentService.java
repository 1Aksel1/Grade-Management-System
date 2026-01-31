package org.university.user_service.services;

import org.university.user_service.dtos.*;

import java.util.List;

public interface StudentService {

    public void registerStudent(CreateStudentDto createStudentDto);

    public UpdateStudentDto updateStudent(String authHeader, UpdateStudentDto updateStudentDto);

    public UpdateStudentDto getStudentForUpdate(String authHeader);

    public Long getStudentIdForEmail(String email);

    public void checkPasswordMatch(String authHeader, String oldPassword);

    public void changePassword(String linkIdentifier);

    public void changeEmail(String authHeader, UpdateEmailDto updateEmailDto);

    public Boolean registerExam(RegisterExamDto registerExamDto);

    public Boolean isExamRegistered(String studentIndex, String courseName, String examPeriod);

    public List<StudentUsernameAndEmailDto> findStudentUsernamesAndEmails();

    public StudentNameAndEmailDto findStudentNameAndEmail(String studentIndex);

    public String getStudentEmail(String authHeader);

    public List<RegisterExamDto> findAllRegisteredExams(String authHeader);

}
