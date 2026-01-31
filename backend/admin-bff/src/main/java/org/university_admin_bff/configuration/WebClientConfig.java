package org.university_admin_bff.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Configuration
public class WebClientConfig {

    private final DiscoveryClient discoveryClient;

    @Autowired
    public WebClientConfig(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @Bean
    public WebClient userServiceWebClient() {

        ServiceInstance serviceInstance = discoveryClient.getInstances("user-service").get(0);

        if(serviceInstance == null) {
            throw new RuntimeException("Can't connect to the user service!");
        }

        return WebClient.builder()
                .baseUrl(serviceInstance.getUri().toString() +  "/api")
                .build();

    }

    @Bean
    public WebClient gradeManagementServiceWebClient() {

        ServiceInstance serviceInstance = discoveryClient.getInstances("grade-management-service").get(0);

        if(serviceInstance == null) {
            throw new RuntimeException("Can't connect to the grade-management service!");
        }

        return WebClient.builder()
                .baseUrl(serviceInstance.getUri().toString() +  "/api")
                .build();

    }

    @Bean
    public WebClient notificationServiceWebClient() {

        ServiceInstance serviceInstance = discoveryClient.getInstances("notification-service").get(0);

        if(serviceInstance == null) {
            throw new RuntimeException("Can't connect to the notification service!");
        }

        return WebClient.builder()
                .baseUrl(serviceInstance.getUri().toString() + "/api")
                .build();

    }

}

