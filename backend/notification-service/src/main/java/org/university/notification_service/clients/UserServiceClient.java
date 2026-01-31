package org.university.notification_service.clients;

import org.university.notification_service.dtos.StudentNameAndEmailDto;
import org.university.notification_service.dtos.StudentUsernameAndEmailDto;

import java.util.List;

public interface UserServiceClient {

    public StudentNameAndEmailDto sendRequestForGradeAddedData(String studentIndex);

    public List<StudentUsernameAndEmailDto> sendRequestForExamPeriodActivationData();

    public String sendRequestForStudentEmail(String authHeader);

    public String sendRequestForProfessorEmail(String authHeader);



}
