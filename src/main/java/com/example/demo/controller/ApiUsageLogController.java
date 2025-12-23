package com.example.demo.controller;

import com.example.demo.dto.ApiUsageLogDto;
import com.example.demo.dto.CountResponseDto;
import com.example.demo.service.ApiUsageLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usage-logs")
public class ApiUsageLogController {
    private final ApiUsageLogService usageLogService;
    public ApiUsageLogController(ApiUsageLogService usageLogService) {
        this.usageLogService = usageLogService;
    }

    @PostMapping
    public ResponseEntity<ApiUsageLogDto> logUsage(@RequestBody ApiUsageLogDto logDto) {
        ApiUsageLogDto response = usageLogService.logUsage(logDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/key/{keyId}")
    public ResponseEntity<List<ApiUsageLogDto>> getUsageForApiKey(@PathVariable Long keyId) {
        List<ApiUsageLogDto> response = usageLogService.getUsageForApiKey(keyId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/key/{keyId}/today")
    public ResponseEntity<List<ApiUsageLogDto>> getUsageForToday(@PathVariable Long keyId) {
        List<ApiUsageLogDto> response = usageLogService.getUsageForToday(keyId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/key/{keyId}/count-today")
    public ResponseEntity<CountResponseDto> countRequestsToday(@PathVariable Long keyId) {
        Long count = usageLogService.countRequestsToday(keyId);
        CountResponseDto response = new CountResponseDto(count);
        return ResponseEntity.ok(response);
    }
}
