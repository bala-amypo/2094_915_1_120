// com/example/demo/security/CustomUserDetailsService.java
package com.example.demo.security;

import java.util.*;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

/**
 * In-memory user details service for authentication.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    // Simple in-memory store: username -> encoded password
    private final Map<String, String> users = new HashMap<>();

    /**
     * Save a new user with the given encoded password.
     */
    public void saveUser(String username, String encodedPassword) {
        users.put(username, encodedPassword);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String password = users.get(username);
        if (password == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        // Return a Spring Security User with one simple ROLE_USER authority
        return new org.springframework.security.core.userdetails.User(
            username,
            password,
            Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
}
