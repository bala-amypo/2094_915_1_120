package com.example.demo.service.impl;

import com.example.demo.dto.ApiUsageLogDto;
import com.example.demo.model.ApiUsageLog;
import com.example.demo.repository.ApiUsageLogRepository;
import com.example.demo.service.ApiUsageLogService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ApiUsageLogServiceImpl implements ApiUsageLogService {

    private final ApiUsageLogRepository apiUsageLogRepository;

    public ApiUsageLogServiceImpl(ApiUsageLogRepository apiUsageLogRepository) {
        this.apiUsageLogRepository = apiUsageLogRepository;
    }

    @Override
    public Long countRequestsToday(Long apiKeyId) {
        // minimal implementation to satisfy interface
        return 0L;
    }

    @Override
    public List<ApiUsageLogDto> getUsageForToday(Long apiKeyId) {
        // minimal implementation
        return Collections.emptyList();
    }

    @Override
    public List<ApiUsageLogDto> getUsageForApiKey(Long apiKeyId) {
        // minimal implementation
        return Collections.emptyList();
    }

    @Override
    public void logUsage(ApiUsageLogDto apiUsageLogDto) {
        // minimal safe persistence
        ApiUsageLog log = new ApiUsageLog();
        apiUsageLogRepository.save(log);
    }
}
