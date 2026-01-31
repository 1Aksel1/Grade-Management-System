package org.university_grade_management_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class PreExamObligation {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer maxPoints;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "course_id")
    private Course course;


    public PreExamObligation(String name, Integer maxPoints, Course course) {
        this.name = name;
        this.maxPoints = maxPoints;
        this.course = course;
    }

    public PreExamObligation() {
    }


}
