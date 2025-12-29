package com.example.demo.controller;

import com.example.demo.entity.ApiUsageLog;
import com.example.demo.service.ApiUsageLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usage-logs")
@RequiredArgsConstructor
public class ApiUsageLogController {

    private final ApiUsageLogService apiUsageLogService;

    @GetMapping
    public ResponseEntity<List<ApiUsageLog>> getAllLogs() {
        return ResponseEntity.ok(apiUsageLogService.getAllLogs());
    }

    @GetMapping("/key/{apiKey}")
    public ResponseEntity<List<ApiUsageLog>> getLogsByApiKey(@PathVariable String apiKey) {
        return ResponseEntity.ok(apiUsageLogService.getLogsByApiKey(apiKey));
    }
}
