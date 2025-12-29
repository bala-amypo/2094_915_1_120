package com.example.demo.controller;

import com.example.demo.entity.ApiUsageLog;
import com.example.demo.service.ApiUsageLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usage")
@RequiredArgsConstructor
public class ApiUsageLogController {

    private final ApiUsageLogService apiUsageLogService;

    @GetMapping
    public List<ApiUsageLog> findAll() {
        return apiUsageLogService.findAll();
    }
}
