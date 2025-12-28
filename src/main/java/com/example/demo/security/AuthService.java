package com.example.demo.security;

import com.example.demo.dto.*;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthResponseDto register(RegisterRequestDto request) {
        // Encode password and save user in memory
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        userDetailsService.saveUser(request.getUsername(), encodedPassword);

        // Generate token for new user
        User user = new User(request.getUsername(), encodedPassword);
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = jwtUtil.generateToken(userDetails);
        return new AuthResponseDto(token);
    }

    public AuthResponseDto login(AuthRequestDto request) {
        UsernamePasswordAuthenticationToken authToken =
            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        authenticationManager.authenticate(authToken);
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = jwtUtil.generateToken(userDetails);
        return new AuthResponseDto(token);
    }
}
