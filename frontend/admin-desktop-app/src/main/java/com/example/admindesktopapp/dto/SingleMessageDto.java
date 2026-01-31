package com.example.admindesktopapp.dto;

public class SingleMessageDto {

    private String message;

    public SingleMessageDto(String message) {
        this.message = message;
    }

    public SingleMessageDto() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}

