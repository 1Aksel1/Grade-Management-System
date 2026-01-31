package org.university_student_client_app.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtil {

    @Value("${oauth.jwt.secret}")
    private String SECRET_KEY;


    public boolean isStudentJwtPresent(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        Cookie[] cookies = httpServletRequest.getCookies();

        if(cookies != null) {

            for(Cookie cookie : cookies) {

                if("jwt".equals(cookie.getName()) && (extractRole(cookie.getValue()).equals("STUDENT"))) {
                    return true;
                } else {
                    cookie.setValue(null);
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    httpServletResponse.addCookie(cookie);
                    return false;
                }

            }

        }

        return false;

    }

    public void refreshJwt(String jwt, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        Cookie[] cookies = httpServletRequest.getCookies();

        if(cookies != null) {

            for(Cookie cookie : cookies) {

                if("jwt".equals(cookie.getName())) {
                    cookie.setValue(jwt);
                    httpServletResponse.addCookie(cookie);
                }

            }

        }

    }

    public String extractJwtFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("jwt".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public String extractJsessionIdFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("JSESSIONID".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

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
}
