package com.springboot.authapiproject.config.jwt;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class JwtProviderTest {


    @InjectMocks
    private JwtProvider jwtProvider;
    private final static String ID = UUID.randomUUID().toString();
    private static final String LOGIN = "testUser";
    private static final String ROLE = "admin";

    @BeforeEach
    void setUp(){
        ReflectionTestUtils.setField(jwtProvider, "jwtSecret", "LkwyCFBzDA8cWUnc9FqSKnESRqXq9mEeJ2QtETNRbqjg" +
                "nv8wT6BpxSAYQzqs87ZKYJCDzr5qUKHz2sbK9QLQt4KxZXJPERxEzrvC6DJ8bDJGn6nq5RbkVwyzSRywXWp5");
    }

    @Test
    void generateToken() {
        String jwtTest = jwtProvider.generateToken(ID,LOGIN,ROLE);

    }

    @Test
    void getRoleFromToken() {
        String token = jwtProvider.generateToken(ID,LOGIN,ROLE);
        Map jwtTest = jwtProvider.parseTokenToMap(token);
    }
}