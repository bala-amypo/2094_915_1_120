package com.example.demo.security;

import com.example.demo.dto.AuthRequestDto;
import com.example.demo.dto.AuthResponseDto;
import com.example.demo.dto.RegisterRequestDto;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public AuthResponseDto login(AuthRequestDto request) {
        return new AuthResponseDto("dummy-token", request.getUsername());
    }

    public AuthResponseDto register(RegisterRequestDto request) {
        return new AuthResponseDto("dummy-token", request.getUsername());
    }
}
