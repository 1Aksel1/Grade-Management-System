package org.university.user_service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.university.user_service.model.Admin;
import org.university.user_service.model.Professor;
import org.university.user_service.model.Role;
import org.university.user_service.model.Student;
import org.university.user_service.repositories.AdminRepository;
import org.university.user_service.repositories.ProfessorRepository;
import org.university.user_service.repositories.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private StudentRepository studentRepository;
    private ProfessorRepository professorRepository;
    private AdminRepository adminRepository;

    @Autowired
    public UserDetailsServiceImpl(StudentRepository studentRepository, ProfessorRepository professorRepository, AdminRepository adminRepository) {
        this.studentRepository = studentRepository;
        this.professorRepository = professorRepository;
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userOptional = retrieveUserByEmail(username);

        if(!userOptional.isPresent()) {
            throw new UsernameNotFoundException("User "+ username +" not found!");
        }

        return userOptional.get();

    }

    public Optional<User> retrieveUserByEmail(String email) {

        List<SimpleGrantedAuthority> authorities =  new ArrayList<>();


        Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);

        if(studentOptional.isPresent() && studentOptional.get().getRegistered().equals(true)) {
            Student student = studentOptional.get();
            authorities.add(new SimpleGrantedAuthority(student.getRole().toString()));

            return Optional.of(new User(student.getEmail(), student.getPassword(), authorities));
        }


        Optional<Professor> professorOptional = professorRepository.findProfessorByEmail(email);

        if(professorOptional.isPresent() && professorOptional.get().getRegistered().equals(true)) {
            Professor professor = professorOptional.get();
            authorities.add(new SimpleGrantedAuthority(professor.getRole().toString()));

            return Optional.of(new User(professor.getEmail(), professor.getPassword(), authorities));

        }

        Optional<Admin> adminOptional = adminRepository.findAdminByEmail(email);

        if(adminOptional.isPresent()) {
            Admin admin = adminOptional.get();
            authorities.add(new SimpleGrantedAuthority(admin.getRole().toString()));

            return Optional.of(new User(admin.getEmail(), admin.getPassword(), authorities));
        }

        return Optional.empty();

    }







}
