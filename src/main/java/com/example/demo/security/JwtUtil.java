package com.example.demo.security;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    public String generateToken(String username) {
        return "dummy-jwt-token";
    }

    public boolean validateToken(String token) {
        return true;
    }
}
