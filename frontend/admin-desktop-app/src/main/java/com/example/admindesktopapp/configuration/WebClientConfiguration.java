package com.example.admindesktopapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration(proxyBeanMethods = false)
public class WebClientConfiguration {

    @Bean
    public WebClient adminBffServiceClient() {

        return WebClient.builder()
                .baseUrl("http://localhost:8080/admin-bff/api")
                .build();

    }



}
