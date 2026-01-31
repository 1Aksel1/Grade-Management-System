package com.example.admindesktopapp.services;

import com.example.admindesktopapp.clients.NotificationServiceClient;
import com.example.admindesktopapp.dto.FilterParametersDto;
import com.example.admindesktopapp.dto.SavedNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
public class SavedNotificationsServiceImpl implements SavedNotificationsService{

    private NotificationServiceClient notificationServiceClient;

    @Autowired
    public SavedNotificationsServiceImpl(NotificationServiceClient notificationServiceClient) {
        this.notificationServiceClient = notificationServiceClient;
    }

    @Override
    public List<SavedNotification> searchSavedNotifications(boolean activationEmail, boolean passwordChange, boolean examPeriodActivated, boolean gradeAdded, String email, String fromDate, String toDate) {

        String uri = getUriWithQueryParameters(new FilterParametersDto(activationEmail, passwordChange, examPeriodActivated, gradeAdded, email, fromDate, toDate));
        return notificationServiceClient.searchSavedNotifications(uri);

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

        if(filterParametersDto.getEmail() != null && filterParametersDto.getEmail() != "") {
            stringBuilder.append("email=").append(filterParametersDto.getEmail()).append("&");
        }

        if(filterParametersDto.getDateFrom() != null && filterParametersDto.getDateFrom() != "") {


            try {

                LocalDate dateFrom = LocalDate.parse(filterParametersDto.getDateFrom());
                LocalDateTime dateTimeFrom = dateFrom.atStartOfDay();

                stringBuilder.append("fromDate=").append(dateTimeFrom.format(isoFormatter)).append("&");

            } catch (DateTimeParseException e) {
                throw new RuntimeException("The pattern of the date must be: yyyy/MM/dd!");
            }

        }

        if(filterParametersDto.getDateTo() != null && filterParametersDto.getDateTo() != "") {


            try {

                LocalDate dateTo = LocalDate.parse(filterParametersDto.getDateTo());
                LocalDateTime dateTimeTo = dateTo.atTime(23, 59, 59);

                stringBuilder.append("toDate=").append(dateTimeTo.format(isoFormatter)).append("&");


            } catch (DateTimeParseException e) {
                throw new RuntimeException("The pattern of the date must be: yyyy/MM/dd!");
            }

        }

        if(stringBuilder.charAt(stringBuilder.length() - 1) == '&') {
            stringBuilder.setLength(stringBuilder.length() - 1);
        }

        return stringBuilder.toString();

    }





}
