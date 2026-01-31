package org.university.user_service.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.university.user_service.model.Admin;
import org.university.user_service.model.Professor;
import org.university.user_service.model.Role;
import org.university.user_service.model.Student;
import org.university.user_service.repositories.AdminRepository;
import org.university.user_service.repositories.ProfessorRepository;
import org.university.user_service.repositories.StudentRepository;

import java.lang.reflect.Type;
import java.util.*;

@Component
public class JwtUtil {

    @Value("${oauth.jwt.secret}")
    private String SECRET_KEY;
    private UserDetailsService userDetailsService;
    private AdminRepository adminRepository;
    private StudentRepository studentRepository;
    private ProfessorRepository professorRepository;

    @Autowired
    public JwtUtil(UserDetailsService userDetailsService, AdminRepository adminRepository, StudentRepository studentRepository, ProfessorRepository professorRepository) {
        this.userDetailsService = userDetailsService;
        this.adminRepository =  adminRepository;
        this.studentRepository = studentRepository;
        this.professorRepository = professorRepository;
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public String extractEmail(String token) {
        return extractAllClaims(token).getSubject();
    }

    public Long extractUserId(String token) {
        return Long.valueOf((Integer) extractAllClaims(token).get("id"));
    }

    public String extractUsername(String token) {return (String) extractAllClaims(token).get("username");}

    public boolean isTokenExpired(String token){
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    public String generateToken(String email){

        Map<String, Object> claims = new HashMap<>();

        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        setClaims(userDetails.getUsername(), userDetails.getAuthorities(), claims);


        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10 * 50))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
    }

    public void setClaims(String email, Collection<? extends GrantedAuthority> authorities, Map<String, Object> claims) {

        String roleString = authorities.iterator().next().getAuthority();
        Role role = Role.valueOf(roleString);

        switch (role) {

            case ADMIN: {

                Optional<Admin> adminOptional = adminRepository.findAdminByEmail(email);

                adminOptional.ifPresent(admin -> {
                    claims.put("id", admin.getId());
                    claims.put("username", admin.getUsername());
                    claims.put("role", admin.getRole().toString());
                });

            }

            break;

            case STUDENT: {

                Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);

                studentOptional.ifPresent(student -> {
                    claims.put("id", student.getId());
                    claims.put("username", student.getUsername());
                    claims.put("indexNumber", student.getIndexNumber());
                    claims.put("role", student.getRole().toString());
                });

            }

            break;

            case PROFESSOR: {

                Optional<Professor> professorOptional = professorRepository.findProfessorByEmail(email);

                professorOptional.ifPresent(professor -> {
                    claims.put("id", professor.getId());
                    claims.put("username", professor.getUsername());
                    claims.put("role", professor.getRole());

                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<String>>() {}.getType();
                    List<String> subjects = gson.fromJson(professor.getSubjects(), listType);
                    claims.put("courses", subjects);


                });

            }

            break;

        }

    }

    public boolean validateToken(String token, UserDetails user) {
        return (user.getUsername().equals(extractEmail(token)) && !isTokenExpired(token));
    }
}
