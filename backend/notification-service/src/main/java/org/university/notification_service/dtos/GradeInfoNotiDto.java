package org.university.notification_service.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class GradeInfoNotiDto {

    @NotBlank(message = "Can't be blank")
    private String studentIndex;

    @NotBlank(message = "Can't be blank")
    private String notificationType;

    @NotBlank(message = "Can't be blank")
    private String courseName;

    @NotNull(message = "Can't be null")
    private Integer grade;

    @NotNull(message = "Can't be null")
    private Integer examPoints;

    @NotNull(message = "Can't be null")
    private List<StudentPreExamScoreDto> preExamScoreDtos = new ArrayList<>();

    public GradeInfoNotiDto(String studentIndex, String courseName, String notificationType, Integer grade, Integer examPoints, List<StudentPreExamScoreDto> preExamScoreDtos) {
        this.studentIndex = studentIndex;
        this.courseName = courseName;
        this.notificationType = notificationType;
        this.grade = grade;
        this.examPoints = examPoints;
        this.preExamScoreDtos = preExamScoreDtos;
    }

    public GradeInfoNotiDto() {
    }


}
