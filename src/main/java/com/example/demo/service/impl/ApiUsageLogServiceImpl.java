package com.example.demo.service.impl;

import com.example.demo.model.ApiKey;
import com.example.demo.model.ApiUsageLog;
import com.example.demo.repository.ApiUsageLogRepository;
import com.example.demo.service.ApiUsageLogService;
import org.springframework.stereotype.Service;

@Service
public class ApiUsageLogServiceImpl implements ApiUsageLogService {

    private final ApiUsageLogRepository apiUsageLogRepository;

    public ApiUsageLogServiceImpl(ApiUsageLogRepository apiUsageLogRepository) {
        this.apiUsageLogRepository = apiUsageLogRepository;
    }

    
    @Override
    public Long countRequestsToday(Long apiKeyId) {
        return 0L;
    }


    @Override
    public Long getUsageForToday(Long apiKeyId) {
        return 0L;
    }


    @Override
    public void logRequest(ApiKey apiKey, String endpoint) {
        ApiUsageLog log = new ApiUsageLog();
        log.setApiKey(apiKey);
        log.setEndpoint(endpoint);
        apiUsageLogRepository.save(log);
    }
}
