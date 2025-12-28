package com.example.demo.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    public String generateToken(UserDetails userDetails) {
        return "token-for-" + userDetails.getUsername();
    }

    public String extractUsername(String token) {
        if (token != null && token.startsWith("token-for-")) {
            return token.substring("token-for-".length());
        }
        return null;
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return username != null && username.equals(userDetails.getUsername());
    }
}
