package org.university_student_client_app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {

    @Bean
    public WebClient userServiceWebClient() {
        return WebClient.builder()
                .baseUrl("http://user-service:8080/api")
                .build();
    }

    @Bean
    public WebClient notificationServiceWebClient() {
        return WebClient.builder()
                .baseUrl("http://notification-service:8082/api")
                .build();
    }

    @Bean
    public WebClient gradeManagementServiceClient() {
        return WebClient.builder()
                .baseUrl("http://grade-management-service:8081/api")
                .build();
    }


    @Bean
    public WebClient studentBffServiceClient() {

        return WebClient.builder()
                .baseUrl("http://student-bff:8082/student-bff/api")
                .build();
    }


}
