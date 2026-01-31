package org.university.user_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "registered_exams", uniqueConstraints = @UniqueConstraint(columnNames = {"courseName", "examPeriod", "student_id"}))
public class RegisteredExam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String courseName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Period examPeriod;

    @JsonIgnore
    @JoinColumn(name = "student_id")
    @ManyToOne
    private Student student;

    public RegisteredExam(String subjectName, Period examPeriod, Student student) {
        this.courseName = subjectName;
        this.examPeriod = examPeriod;
        this.student = student;
    }

    public RegisteredExam() {
    }
}
