package org.university_grade_management_service.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class ExamPeriod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private Period period;

    @Column(nullable = false)
    private Boolean status;

    public ExamPeriod(Period period, Boolean status) {
        this.period = period;
        this.status = status;
    }

    public ExamPeriod() {
    }
}
