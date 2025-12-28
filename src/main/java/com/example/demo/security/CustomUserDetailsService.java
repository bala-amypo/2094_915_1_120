package com.example.demo.security;

import com.example.demo.model.UserAccount;
import com.example.demo.repository.UserAccountRepository;

public class CustomUserDetailsService {

    private final UserAccountRepository repo;

    public CustomUserDetailsService(UserAccountRepository repo) {
        this.repo = repo;
    }

    public UserAccount loadUserByUsername(String email) {
        return repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
