package org.university_professor_bff.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class Grade {

    private Long id;
    private String studentIndex;
    private Integer grade;
    private String examPeriod;
    private Integer examPoints;


}
