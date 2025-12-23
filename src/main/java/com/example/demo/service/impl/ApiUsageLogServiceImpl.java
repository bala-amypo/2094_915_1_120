package com.example.demo.service.impl;

import com.example.demo.dto.ApiUsageLogDto;
import com.example.demo.model.ApiKey;
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

    /**
     * REQUIRED by interface
     */
    @Override
    public Long countRequestsToday(Long apiKeyId) {
        return 0L;
    }

    /**
     * REQUIRED by interface
     */
    @Override
    public List<ApiUsageLogDto> getUsageForToday(Long apiKeyId) {
        return Collections.emptyList();
    }

    /**
     * REQUIRED by interface
     */
    @Override
    public List<ApiUsageLogDto> getUsageForApiKey(Long apiKeyId) {
        return Collections.emptyList();
    }

    /**
     * REQUIRED by interface
     */
    @Override
    public void logRequest(ApiKey apiKey, String endpoint) {
        ApiUsageLog log = new ApiUsageLog();
        log.setApiKey(apiKey);
        log.setEndpoint(endpoint);
        apiUsageLogRepository.save(log);
    }
}
