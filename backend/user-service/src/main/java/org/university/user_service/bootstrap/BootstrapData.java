package org.university.user_service.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.university.user_service.model.*;
import org.university.user_service.repositories.AdminRepository;
import org.university.user_service.repositories.ProfessorRepository;
import org.university.user_service.repositories.RegisteredExamRepository;
import org.university.user_service.repositories.StudentRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class BootstrapData implements CommandLineRunner {


    private AdminRepository adminRepository;
    private StudentRepository studentRepository;
    private ProfessorRepository professorRepository;
    private RegisteredExamRepository registeredExamRepository;
    private PasswordEncoder passwordEncoder;


    @Autowired
    public BootstrapData(AdminRepository adminRepository, StudentRepository studentRepository, ProfessorRepository professorRepository,RegisteredExamRepository registeredExamRepository,  PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.studentRepository = studentRepository;
        this.professorRepository = professorRepository;
        this.registeredExamRepository = registeredExamRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void run(String... args) throws Exception {

        List<Student> students = new ArrayList<>();
        List<Professor> professors = new ArrayList<>();
        List<Admin> admins = new ArrayList<>();

        Admin a1 = new Admin("Danko","Danković","admin", passwordEncoder.encode("admin123"),"admin@demo.com","+38149285043", LocalDate.of(1970,5,13));
        Admin a2 = new Admin("Emma", "Strong", "mainAdmin", passwordEncoder.encode("admin123"), "mainadmin@demo.com", "+381093420542", LocalDate.of(1980,2,27));

        admins.add(a1);
        admins.add(a2);

        Student s1 = new Student("Aksel","Vlaović","akselVlaovic", "RI26/19",  passwordEncoder.encode("student123"),"student@demo.com","+381613160816", LocalDate.of(1999,2,15),true);
        Student s2 = new Student("Marko","Marković","student", "RI27/19", passwordEncoder.encode("student"),"mmarkovic2719ri@rafdemo.rs","+381667495130", LocalDate.of(1999,12,1),true);
        Student s3 = new Student("Bojan","Otović","bojanOtović", "RI111/20", passwordEncoder.encode("student"),"botovic11120ri@rafdemo.rs","+38166742513", LocalDate.of(1999,12,1),true);
        Student s4 = new Student("Lidija","Žarković","lidijaŽarković", "RI10/21", passwordEncoder.encode("student"),"lzarkovic1021ri@rafdemo.rs","+381667499523", LocalDate.of(1999,12,1),true);

        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);


        Professor p1 = new Professor("Ivana", "Ivanović", "professor", passwordEncoder.encode("professor123"), "professor@demo.com", "+381530192095", LocalDate.of(1970,5,14), LocalDate.of(2003,8,9), "[\"Mikroservisne aplikacije\", \"Arhitektura računara\"]", true);
        Professor p2 = new Professor("Milan", "Milanović", "professor2", passwordEncoder.encode("professor"), "professor2@rafdemo.rs", "+381687561327", LocalDate.of(1968,3,20), LocalDate.of(2008,3,13), "[\"Razvoj servisa u oblaku\", \"Napredno web programiranje\"]", true);

        professors.add(p1);
        professors.add(p2);

        adminRepository.saveAll(admins);
        studentRepository.saveAll(students);
        professorRepository.saveAll(professors);

    }

}

