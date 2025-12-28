package com.example.demo.service.impl;

import com.example.demo.entity.ApiUsageLog;
import com.example.demo.repository.ApiUsageLogRepository;
import com.example.demo.service.ApiUsageLogService;

import java.time.LocalDate;
import java.time.ZoneId;
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
                .filter(log -> log.getApiKey().getId().equals(apiKeyId))
                .collect(Collectors.toList());
    }

    @Override
    public int countRequestsToday(Long apiKeyId) {
        LocalDate today = LocalDate.now();

        return (int) repository.findAll()
                .stream()
                .filter(log ->
                        log.getApiKey().getId().equals(apiKeyId) &&
                        log.getTimestamp()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate()
                                .equals(today))
                .count();
    }
}
