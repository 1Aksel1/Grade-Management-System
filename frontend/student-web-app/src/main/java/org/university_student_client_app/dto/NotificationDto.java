package org.university_student_client_app.dto;

import lombok.Data;

@Data
public class NotificationDto {

    private Long id;
    private String email;
    private String subject;
    private String content;
    private String sentTimeAndDate;



}
