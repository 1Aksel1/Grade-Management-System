package org.university_professor_bff.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SavedNotification {

    private Long id;
    private String email;
    private String subject;
    private String content;
    private LocalDateTime sentTimeAndDate;


}
