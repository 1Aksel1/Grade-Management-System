package org.university.user_service.dtos;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class ChangePasswordNotiDto {

    @NotBlank(message = "Can't be blank")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Can't be blank")
    private String notificationType;

    @NotBlank(message = "Can't be blank")
    private String username;

    @NotBlank(message = "Can't be blank")
    private String confirmationLink;

    public ChangePasswordNotiDto(String email, String notificationType, String username, String confirmationLink) {
        this.email = email;
        this.notificationType = notificationType;
        this.username = username;
        this.confirmationLink = confirmationLink;
    }

    public ChangePasswordNotiDto() {
    }

}
