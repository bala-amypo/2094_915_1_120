package com.example.demo.service.impl;

import com.example.demo.entity.ApiUsageLog;
import com.example.demo.repository.ApiUsageLogRepository;
import com.example.demo.service.ApiUsageLogService;

import java.util.List;

public class ApiUsageLogServiceImpl implements ApiUsageLogService {

    private final ApiUsageLogRepository repository;

    public ApiUsageLogServiceImpl(ApiUsageLogRepository repository) {
        this.repository = repository;
    }

    @Override
    public ApiUsageLog save(ApiUsageLog log) {
        return repository.save(log);
    }

    @Override
    public List<ApiUsageLog> findAll() {
        return repository.findAll();
    }
}
