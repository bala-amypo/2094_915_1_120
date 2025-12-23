package com.example.demo.service.impl;

import com.example.demo.entity.ApiUsageLog;
import com.example.demo.repository.ApiUsageLogRepository;
import com.example.demo.service.ApiUsageLogService;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.ZonedDateTime;

@Service
public class ApiUsageLogServiceImpl implements ApiUsageLogService {

    private final ApiUsageLogRepository apiUsageLogRepository;

    public ApiUsageLogServiceImpl(ApiUsageLogRepository apiUsageLogRepository) {
        this.apiUsageLogRepository = apiUsageLogRepository;
    }

    @Override
    public long countRequests(String apiKey, long windowMillis) {
        ZonedDateTime now = ZonedDateTime.now();

        ZonedDateTime fromTime = now.minus(Duration.ofMillis(windowMillis));

        return apiUsageLogRepository
                .countByApiKeyAndRequestTimeBetween(apiKey, fromTime, now);
    }

    @Override
    public void logRequest(String apiKey, String endpoint) {
        ApiUsageLog log = new ApiUsageLog();
        log.setApiKey(apiKey);
        log.setEndpoint(endpoint);
        log.setRequestTime(ZonedDateTime.now());

        apiUsageLogRepository.save(log);
    }

    @Override
    public long countRequestsForEndpoint(
            String apiKey,
            String endpoint,
            long windowMillis
    ) {
        ZonedDateTime now = ZonedDateTime.now();

        ZonedDateTime fromTime = now.minus(Duration.ofMillis(windowMillis));

        return apiUsageLogRepository
                .countByApiKeyAndEndpointAndRequestTimeBetween(
                        apiKey,
                        endpoint,
                        fromTime,
                        now
                );
    }
}
