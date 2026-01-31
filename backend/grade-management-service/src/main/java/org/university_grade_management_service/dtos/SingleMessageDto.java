package org.university_grade_management_service.dtos;

import lombok.Data;

@Data
public class SingleMessageDto {

    private String message;

    public SingleMessageDto(String message) {
        this.message = message;
    }


}
