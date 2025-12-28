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
    public void logUsage(ApiUsageLog log) {
        repository.save(log);
    }

    @Override
    public List<ApiUsageLog> getUsageForApiKey(Long apiKeyId) {
        return repository.findAll()
                .stream()
                .filter(log ->
                        log.getApiKey() != null &&
                        log.getApiKey().getKeyId().equals(apiKeyId))
                .collect(Collectors.toList());
    }

    @Override
    public List<ApiUsageLog> getUsageForToday(Long apiKeyId) {
        LocalDate today = LocalDate.now();

        return repository.findAll()
                .stream()
                .filter(log ->
                        log.getApiKey() != null &&
                        log.getApiKey().getKeyId().equals(apiKeyId) &&
                        log.getTimestamp()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate()
                                .equals(today))
                .collect(Collectors.toList());
    }

    @Override
    public int countRequestsToday(Long apiKeyId) {
        return getUsageForToday(apiKeyId).size();
    }
}
