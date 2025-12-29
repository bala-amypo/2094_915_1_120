package com.example.demo.controller;

import com.example.demo.entity.Quota;
import com.example.demo.service.QuotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quota")
@RequiredArgsConstructor
public class QuotaController {

    private final QuotaService quotaService;

    @GetMapping("/{apiKey}")
    public ResponseEntity<Quota> getQuota(@PathVariable String apiKey) {
        return ResponseEntity.ok(quotaService.getQuotaByApiKey(apiKey));
    }

    @PostMapping("/reset/{apiKey}")
    public ResponseEntity<String> resetQuota(@PathVariable String apiKey) {
        quotaService.resetQuota(apiKey);
        return ResponseEntity.ok("Quota reset successfully");
    }
}
