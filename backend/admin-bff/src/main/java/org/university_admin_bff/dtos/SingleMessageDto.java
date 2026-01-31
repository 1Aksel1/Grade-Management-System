package org.university_admin_bff.dtos;

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
