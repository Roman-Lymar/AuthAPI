package com.springboot.authapiproject.config.jwt;


import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtProvider {

    @Value("$(jwt.secret)")
    private String jwtSecret;

    public String generateToken(String id, String login, String role) {

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(id)
                .claim("role", role)
                .setIssuedAt(Date.from(Instant.ofEpochSecond(1466796822L)))
                .setExpiration(Date.from(Instant.ofEpochSecond(4622470422L)))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
//            log.severe("invalid token");
        }
        return false;
    }

    public String getIdFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public Map<String,Object> parseTokenToMap(String token) {
        Claims tokenBody = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return new HashMap<>(tokenBody);
    }

    public String getRoleFromToken(String token){
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.get("role",String.class);
    }
}
