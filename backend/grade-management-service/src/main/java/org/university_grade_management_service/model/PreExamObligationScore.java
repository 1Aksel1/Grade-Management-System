package org.university_grade_management_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class PreExamObligationScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String studentIndex;

    @Column(nullable = false)
    private Integer pointsScored;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public PreExamObligationScore(String name, String studentIndex, Integer pointsScored, Course course) {
        this.name = name;
        this.studentIndex = studentIndex;
        this.pointsScored = pointsScored;
        this.course = course;
    }

    public PreExamObligationScore() {

    }
}
