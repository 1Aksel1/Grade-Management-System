package org.university.notification_service.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class NotificationTypeDto {

    private Long id;

    @NotBlank(message = "Can't be blank")
    @Size(min = 2, max = 40, message = "Must be between 2 and 40 characters")
    private String typeName;

    @NotBlank(message = "Can't be blank")
    @Size(min = 2, max = 50, message = "Must be between 2 and 50 characters")
    private String subject;

    @NotBlank(message = "Can't be blank")
    @Size(min = 2, max = 1000, message = "Must be between 2 and 1000 characters")
    private String template;

    @NotBlank(message = "Can't be blank")
    @Size(min = 2, max = 200, message = "Must be between 2 and 200 characters")
    private String parameters;


}
