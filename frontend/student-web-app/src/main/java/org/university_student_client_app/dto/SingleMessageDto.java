package org.university_student_client_app.dto;

import lombok.Data;

@Data
public class SingleMessageDto {

    private String message;

    public SingleMessageDto(String message) {
        this.message = message;
    }

    public SingleMessageDto() {
    }



}
