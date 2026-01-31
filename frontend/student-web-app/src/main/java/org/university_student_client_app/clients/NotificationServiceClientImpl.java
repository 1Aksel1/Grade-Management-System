package org.university_student_client_app.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.university_student_client_app.dto.FilterParametersDto;
import org.university_student_client_app.dto.NotificationDto;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class NotificationServiceClientImpl implements NotificationServiceClient {

    private WebClient studentBffServiceClient;

    @Autowired
    public NotificationServiceClientImpl(WebClient studentBffServiceClient) {
        this.studentBffServiceClient = studentBffServiceClient;
    }

    @Override
    public List<NotificationDto> getStudentNotifications(String jwt, FilterParametersDto filterParametersDto) {

        String uri = getUriWithQueryParameters(filterParametersDto);

        StringBuilder stringBuilder = new StringBuilder("Bearer ");
        stringBuilder.append(jwt);

        String authHeader = stringBuilder.toString();

        return this.studentBffServiceClient
                .get()
                .uri(uri)
                .header("Authorization", authHeader)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> {
                    System.out.println("Client error");

                    return clientResponse.bodyToMono(String.class)
                            .flatMap(errorMessage -> Mono.error(new RuntimeException("Client Error: " + errorMessage)));
                })
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> {
                    System.out.println("Server error");

                    return clientResponse.bodyToMono(String.class)
                            .flatMap(errorMessage -> Mono.error(new RuntimeException("Server error: " + errorMessage)));
                })
                .bodyToFlux(NotificationDto.class)
                .collectList()
                .block();

    }


    private String getUriWithQueryParameters(FilterParametersDto filterParametersDto) {

        StringBuilder stringBuilder = new StringBuilder("/savedNotifications?");
        DateTimeFormatter isoFormatter = DateTimeFormatter.ISO_DATE_TIME;

        if(filterParametersDto.isAccountActivation()) {
            stringBuilder.append("activation_email=true&");
        }

        if(filterParametersDto.isPasswordChange()) {
            stringBuilder.append("password_change=true&");
        }

        if(filterParametersDto.isExamPeriodActivation()) {
            stringBuilder.append("exam_period_activated=true&");
        }

        if(filterParametersDto.isGradeAdded()) {
            stringBuilder.append("grade_added=true&");
        }

        if(filterParametersDto.getDateFrom() != null && filterParametersDto.getDateFrom() != "") {
            LocalDate dateFrom = LocalDate.parse(filterParametersDto.getDateFrom());
            LocalDateTime dateTimeFrom = dateFrom.atStartOfDay();

            stringBuilder.append("fromDate=").append(dateTimeFrom.format(isoFormatter)).append("&");
        }

        if(filterParametersDto.getDateTo() != null && filterParametersDto.getDateTo() != "") {
            LocalDate dateTo = LocalDate.parse(filterParametersDto.getDateTo());
            LocalDateTime dateTimeTo = dateTo.atTime(23, 59, 59);

            stringBuilder.append("toDate=").append(dateTimeTo.format(isoFormatter)).append("&");
        }

        if(stringBuilder.charAt(stringBuilder.length() - 1) == '&') {
            stringBuilder.setLength(stringBuilder.length() - 1);
        }

        return stringBuilder.toString();

    }


}
