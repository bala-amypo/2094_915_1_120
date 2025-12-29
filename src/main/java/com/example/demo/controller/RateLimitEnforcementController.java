package com.example.demo.controller;

import com.example.demo.service.RateLimitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rate-limit")
@RequiredArgsConstructor
public class RateLimitController {

    private final RateLimitService rateLimitService;

    @GetMapping("/check")
    public ResponseEntity<String> checkLimit(@RequestParam String apiKey) {
        boolean allowed = rateLimitService.isRequestAllowed(apiKey);
        return allowed
                ? ResponseEntity.ok("Request allowed")
                : ResponseEntity.status(429).body("Rate limit exceeded");
    }
}
