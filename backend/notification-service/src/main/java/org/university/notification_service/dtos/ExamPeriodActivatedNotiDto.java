package org.university.notification_service.dtos;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class ExamPeriodActivatedNotiDto {

    @NotBlank(message = "Can't be blank")
    private String notificationType;

    @NotBlank(message = "Can't be blank")
    private String examPeriod;

    public ExamPeriodActivatedNotiDto(String notificationType, String examPeriod) {
        this.notificationType = notificationType;
        this.examPeriod = examPeriod;
    }

    public ExamPeriodActivatedNotiDto() {
    }

}
