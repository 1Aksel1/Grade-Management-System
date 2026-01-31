package org.university_admin_bff.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
