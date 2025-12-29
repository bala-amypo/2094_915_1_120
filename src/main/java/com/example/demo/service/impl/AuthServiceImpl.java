package com.example.demo.service.impl;

import com.example.demo.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public String authenticate(String apiKey) {
        // TEMP logic — replace later
        if (apiKey == null || apiKey.isBlank()) {
            throw new RuntimeException("API key is missing");
        }
        return "Authenticated with key: " + apiKey;
    }
}
