package com.example.demo.service.impl;

import com.example.demo.entity.ApiUsageLog;
import com.example.demo.repository.ApiUsageLogRepository;
import com.example.demo.service.ApiUsageLogService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ApiUsageLogServiceImpl implements ApiUsageLogService {

    private final ApiUsageLogRepository repository;

    public ApiUsageLogServiceImpl(ApiUsageLogRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ApiUsageLog> getUsageForApiKey(Long apiKeyId) {
        return repository.findAll()
                .stream()
                .filter(log -> log.apiKeyId.equals(apiKeyId))
                .collect(Collectors.toList());
    }

    @Override
    public long countRequestsToday(Long apiKeyId) {
        LocalDate today = LocalDate.now();

        return repository.findAll()
                .stream()
                .filter(log ->
                        log.apiKeyId.equals(apiKeyId) &&
                        log.timestamp.toLocalDate().equals(today))
                .count();
    }
}
