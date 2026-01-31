package org.university_admin_bff.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JwtUtil {

    @Value("${oauth.jwt.secret}")
    private String SECRET_KEY;

    @Value("${oauth.adminHeader}")
    private String adminAuthHeader;

    public Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public String extractEmail(String token) {
        return extractAllClaims(token).getSubject();
    }

    public List<String> extractCourses(String token) { return (List<String>) extractAllClaims(token).get("courses");}

    public String extractStudentIndex(String token) {
        return (String) extractAllClaims(token).get("indexNumber");
    }

    public Long extractUserId(String token) {
        return Long.valueOf((Integer) extractAllClaims(token).get("id"));
    }

    public boolean isTokenExpired(String token){
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    public boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

    public String extractRole(String token) {
        return (String) extractAllClaims(token).get("role");
    }

    public boolean isRoleAdmin(String token) {

        return extractRole(token).equals("ADMIN");

    }

    public String getAdminAuthHeader() {
        return adminAuthHeader;
    }

}
