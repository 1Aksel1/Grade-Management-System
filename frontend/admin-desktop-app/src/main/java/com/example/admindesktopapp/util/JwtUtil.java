package com.example.admindesktopapp.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    @Value("${oauth.jwt.secret}")
    private String SECRET_KEY;

    private String jwt;

    private String authHeader;

    public Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public String extractRole(String token) {
        return (String) extractAllClaims(token).get("role");
    }


    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;

        StringBuilder stringBuilder = new StringBuilder("Bearer ");
        stringBuilder.append(jwt);
        setAuthHeader(stringBuilder.toString());

    }

    public String getAuthHeader() {
        return authHeader;
    }

    public void setAuthHeader(String authHeader) {
        this.authHeader = authHeader;
    }

    public void removeJwtAndAuthHeader() {
        this.jwt = null;
        this.authHeader = null;
    }

}
