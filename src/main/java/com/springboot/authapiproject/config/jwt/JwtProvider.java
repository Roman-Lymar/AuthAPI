package com.springboot.authapiproject.config.jwt;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.*;
import java.time.Instant;
import java.util.Date;

@Component
public class JwtProvider {

//    @Autowired
//    private RsaUtils rsaUtils;

    private static String PUBLIC_KEY_PATH = "src/main/resources/rsa_public.key";
    private static String PRIVATE_KEY_PATH = "src/main/resources/rsa_private.key";

    public String generateToken(String id, String role) throws UnsupportedEncodingException {

        //rsaUtils.createRsaKeys();

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(id)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.ofEpochSecond(12622470422L)))
                .signWith(getPrivateKey(), SignatureAlgorithm.RS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getPublicKey()).build().parseClaimsJws(token);
            return true;
        } catch (Exception  e) {

        }
        return false;
    }

    public String getIdFromToken(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(getPublicKey()).build().parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    private PrivateKey getPrivateKey() {
        File privateKeyFile = new File(PRIVATE_KEY_PATH);
        byte[] privateKeyBytes = new byte[0];

        try {
            privateKeyBytes = Files.readAllBytes(privateKeyFile.toPath());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
            return keyFactory.generatePrivate(privateKeySpec);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    private PublicKey getPublicKey() {
        File publicKeyFile = new File(PUBLIC_KEY_PATH);
        byte[] publicKeyBytes = new byte[0];

        try {
            publicKeyBytes = Files.readAllBytes(publicKeyFile.toPath());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
            return keyFactory.generatePublic(publicKeySpec);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }
}
