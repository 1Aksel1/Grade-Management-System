package org.university.notification_service.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    private DiscoveryClient discoveryClient;

    @Autowired
    public WebClientConfig(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @Bean
    public WebClient userServiceWebClient() {

        ServiceInstance serviceInstance = discoveryClient.getInstances("user-service").get(0);

        if(serviceInstance == null) {
            throw new RuntimeException("Can't connect to the user service");
        }

        return WebClient.builder()
                .baseUrl(serviceInstance.getUri().toString() + "/api")
                .build();
    }


}
