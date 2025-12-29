package com.example.demo.controller;

import com.example.demo.entity.KeyExemption;
import com.example.demo.service.KeyExemptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exemptions")
@RequiredArgsConstructor
public class KeyExemptionController {

    private final KeyExemptionService keyExemptionService;

    @PostMapping("/{apiKeyId}")
    public ResponseEntity<KeyExemption> addExemption(@PathVariable Long apiKeyId) {
        return ResponseEntity.ok(keyExemptionService.addExemption(apiKeyId));
    }

    @GetMapping
    public ResponseEntity<List<KeyExemption>> getAllExemptions() {
        return ResponseEntity.ok(keyExemptionService.getAllExemptions());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeExemption(@PathVariable Long id) {
        keyExemptionService.removeExemption(id);
        return ResponseEntity.ok("Exemption removed");
    }
}
