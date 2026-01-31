package org.university_grade_management_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class StudentEnrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String studentIndex;

    @Enumerated(EnumType.STRING)
    private EnrollmentStatus status;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public StudentEnrollment(String studentIndex, EnrollmentStatus status, Course course) {
        this.studentIndex = studentIndex;
        this.status = status;
        this.course = course;
    }

    public StudentEnrollment() {

    }
}
