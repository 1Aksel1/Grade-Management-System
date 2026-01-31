package org.university_grade_management_service.configuration;

import io.github.resilience4j.core.IntervalFunction;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.crossstore.ChangeSetPersister;

@Configuration
public class RetryConfiguration {

    @Bean
    public io.github.resilience4j.retry.Retry registerExamRetry() {

        RetryConfig retryConfig = RetryConfig.custom()
                .intervalFunction(IntervalFunction.ofExponentialBackoff(2000, 2))
                .maxAttempts(4)
                .ignoreExceptions(ChangeSetPersister.NotFoundException.class)
                .build();

        RetryRegistry retryRegistry = RetryRegistry.of(retryConfig);

        return retryRegistry.retry("registerExamRetry");

    }

    @Bean
    public Retry isExamRegisteredRetry() {

        RetryConfig retryConfig = RetryConfig.custom()
                .intervalFunction(IntervalFunction.ofExponentialBackoff(5000, 2))
                .maxAttempts(3)
                .ignoreExceptions(ChangeSetPersister.NotFoundException.class)
                .build();

        RetryRegistry retryRegistry = RetryRegistry.of(retryConfig);


        return retryRegistry.retry("isExamRegisteredRetry");

    }

}
