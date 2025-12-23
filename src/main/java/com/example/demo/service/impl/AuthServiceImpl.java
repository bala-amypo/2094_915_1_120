package com.example.demo.service.impl;

import com.example.demo.dto.*;
import com.example.demo.entity.UserAccount;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.service.AuthService;

import java.util.Map;

public class AuthServiceImpl implements AuthService {

    private final UserAccountRepository repo;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(UserAccountRepository repo, JwtUtil jwtUtil) {
        this.repo = repo;
        this.jwtUtil = jwtUtil;
    }

    public void register(RegisterRequestDto dto) {
        if (repo.existsByEmail(dto.getEmail()))
            throw new BadRequestException("Email exists");

        UserAccount user = new UserAccount();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());

        repo.save(user);
    }

    public AuthResponseDto login(AuthRequestDto dto) {
        UserAccount user = repo.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid"));

        String token = jwtUtil.generateToken(Map.of(), user.getEmail());
        return new AuthResponseDto(token);
    }
}
