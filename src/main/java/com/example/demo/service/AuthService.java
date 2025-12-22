package com.example.demo.service;

import com.example.demo.dto.*;

public interface AuthService {
    void register(RegisterRequestDto dto);
    AuthResponseDto login(AuthRequestDto dto);
}
