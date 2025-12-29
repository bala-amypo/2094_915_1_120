package com.example.demo.controller;

import com.example.demo.entity.ApiKey;
import com.example.demo.service.ApiKeyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-keys")
@RequiredArgsConstructor
public class ApiKeyController {

    private final ApiKeyService apiKeyService;

    @PostMapping("/{userId}")
    public ResponseEntity<ApiKey> generateKey(@PathVariable Long userId) {
        return ResponseEntity.ok(apiKeyService.generateKey(userId));
    }

    @GetMapping
    public ResponseEntity<List<ApiKey>> getAllKeys() {
        return ResponseEntity.ok(apiKeyService.getAllKeys());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> revokeKey(@PathVariable Long id) {
        apiKeyService.revokeKey(id);
        return ResponseEntity.ok("API Key revoked");
    }
}
