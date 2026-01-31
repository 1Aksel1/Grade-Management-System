package org.university.notification_service.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.university.notification_service.dtos.StudentNameAndEmailDto;
import org.university.notification_service.dtos.StudentUsernameAndEmailDto;

import java.util.List;

@Component
public class UserServiceClientImpl implements UserServiceClient {

    private WebClient userServiceWebClient;

    @Value("${oauth.adminHeader}")
    private String adminAuthHeader;

    @Autowired
    public UserServiceClientImpl(WebClient userServiceWebClient) {
        this.userServiceWebClient = userServiceWebClient;
    }


    public StudentNameAndEmailDto sendRequestForGradeAddedData(String studentIndex) {

        StringBuilder stringBuilder = new StringBuilder("/student/getNameAndEmail?studentIndex=");
        stringBuilder.append(studentIndex);
        String uri = stringBuilder.toString();

        return this.userServiceWebClient
                .get()
                .uri(uri)
                .header("Authorization", adminAuthHeader)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<StudentNameAndEmailDto>() {})
                .block()
                .getBody();
    }

    public List<StudentUsernameAndEmailDto> sendRequestForExamPeriodActivationData() {

        String uri = new String("/student/getAllUsernamesAndEmails");

        return this.userServiceWebClient
                .get()
                .uri(uri)
                .header("Authorization", adminAuthHeader)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<StudentUsernameAndEmailDto>>() {})
                .block()
                .getBody();


    }

    public String sendRequestForStudentEmail(String authHeader) {

        String uri = "/student/email";

        return this.userServiceWebClient
                .get()
                .uri(uri)
                .header("Authorization", authHeader)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<String>() {})
                .block()
                .getBody();

    }

    public String sendRequestForProfessorEmail(String authHeader) {

        String uri = "/professor/email";

        return this.userServiceWebClient
                .get()
                .uri(uri)
                .header("Authorization", authHeader)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<String>() {})
                .block()
                .getBody();

    }
}
