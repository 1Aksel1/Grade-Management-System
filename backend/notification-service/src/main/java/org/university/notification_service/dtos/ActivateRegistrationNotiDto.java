package org.university.notification_service.dtos;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class ActivateRegistrationNotiDto {

    @NotBlank(message = "Can't be blank")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Can't be blank")
    private String notificationType;

    @NotBlank(message = "Can't be blank")
    private String name;

    @NotBlank(message = "Can't be blank")
    private String surname;

    @NotBlank(message = "Can't be blank")
    private String activationLink;

    public ActivateRegistrationNotiDto(String email, String notificationType, String name, String surname, String activationLink) {
        this.email = email;
        this.notificationType = notificationType;
        this.name = name;
        this.surname = surname;
        this.activationLink = activationLink;
    }

    public ActivateRegistrationNotiDto() {
    }


}
