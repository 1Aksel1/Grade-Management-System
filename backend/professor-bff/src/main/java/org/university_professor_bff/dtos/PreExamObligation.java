package org.university_professor_bff.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class PreExamObligation {

    private Long id;
    private String name;
    private Integer maxPoints;

}
