package org.university_professor_bff.dtos;

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
