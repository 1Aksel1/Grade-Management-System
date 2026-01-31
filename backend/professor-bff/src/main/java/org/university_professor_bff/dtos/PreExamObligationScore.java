package org.university_professor_bff.dtos;

import lombok.Data;

@Data
public class PreExamObligationScore {

    private Long id;
    private String name;
    private String studentIndex;
    private Integer pointsScored;

}
