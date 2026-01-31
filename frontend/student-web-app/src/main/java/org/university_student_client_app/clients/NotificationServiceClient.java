package org.university_student_client_app.clients;

import org.university_student_client_app.dto.FilterParametersDto;
import org.university_student_client_app.dto.NotificationDto;

import java.util.List;

public interface NotificationServiceClient {

    public List<NotificationDto> getStudentNotifications(String jwt, FilterParametersDto filterParametersDto);

}
