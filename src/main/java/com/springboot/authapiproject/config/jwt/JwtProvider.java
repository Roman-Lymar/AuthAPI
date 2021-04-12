package com.springboot.authapiproject.config.jwt;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;

@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    public String generateToken(String id, String role) throws UnsupportedEncodingException {

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(id)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.ofEpochSecond(12622470422L)))
                .signWith(getEncodedSecretKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getEncodedSecretKey()).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {

        }
        return false;
    }

    public String getIdFromToken(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(getEncodedSecretKey()).build().parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    private String getSecretKey() {
        return jwtSecret;
    }

    private SecretKey getEncodedSecretKey() {
        return Keys.hmacShaKeyFor(getSecretKey().getBytes(StandardCharsets.UTF_8));
    }
}
