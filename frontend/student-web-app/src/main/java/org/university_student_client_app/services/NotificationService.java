package org.university_student_client_app.services;

import org.university_student_client_app.dto.FilterParametersDto;
import org.university_student_client_app.dto.NotificationDto;

import java.util.List;

public interface NotificationService {

    public List<NotificationDto> getStudentNotifications(String jwt, FilterParametersDto filterParametersDto);

}
