package org.university.user_service.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String indexNumber;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String telephoneNumber;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role = Role.STUDENT;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RegisteredExam> registeredExams = new ArrayList<>();

    @Column(nullable = false)
    private Boolean registered = false;

    public Student(String name, String surname, String username, String indexNumber, String password, String email, String telephoneNumber, LocalDate dateOfBirth, Boolean registered) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.indexNumber = indexNumber;
        this.password = password;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.registered = registered;
    }

    public Student() {}
}
