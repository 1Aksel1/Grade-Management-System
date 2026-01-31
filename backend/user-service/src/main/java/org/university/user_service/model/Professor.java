package org.university.user_service.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String telephoneNumber;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private LocalDate hireDate;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String subjects;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role = Role.PROFESSOR;

    @Column(nullable = false)
    private Boolean registered = false;

    public Professor(String name, String surname, String username, String password, String email, String telephoneNumber, LocalDate dateOfBirth, LocalDate hireDate, String subjects, Boolean registered) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.hireDate = hireDate;
        this.subjects = subjects;
        this.registered = registered;
    }

    public Professor() {}
}
