package com.example.demo.security;

import com.example.demo.entity.UserAccount;
import com.example.demo.repository.UserAccountRepository;

public class CustomUserDetailsService {

    private final UserAccountRepository userRepo;

    public CustomUserDetailsService(UserAccountRepository userRepo) {
        this.userRepo = userRepo;
    }

    public UserAccount loadUserByUsername(String email) {
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
