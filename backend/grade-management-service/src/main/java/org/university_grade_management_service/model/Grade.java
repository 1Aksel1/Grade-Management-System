package org.university_grade_management_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Data
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String studentIndex;

    @Column(nullable = false)
    private Integer grade;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Period examPeriod;

    @Column(nullable = false)
    private Integer examPoints;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public Grade(String studentIndex, Integer grade, Integer examPoints, Period examPeriod, Course course) {
        this.studentIndex = studentIndex;
        this.grade = grade;
        this.examPoints = examPoints;
        this.examPeriod = examPeriod;
        this.course = course;
    }

    public Grade() {
    }


}
