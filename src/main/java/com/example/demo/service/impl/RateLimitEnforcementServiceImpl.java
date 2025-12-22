package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.RateLimitEnforcementService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class RateLimitEnforcementServiceImpl implements RateLimitEnforcementService {

    private final ApiUsageLogRepository usageLogRepository;
    private final ApiKeyRepository apiKeyRepository;
    private final KeyExemptionRepository exemptionRepository;

    // Constructor Injection is required [cite: 8]
    public RateLimitEnforcementServiceImpl(ApiUsageLogRepository usageLogRepository, 
                                           ApiKeyRepository apiKeyRepository,
                                           KeyExemptionRepository exemptionRepository) {
        this.usageLogRepository = usageLogRepository;
        this.apiKeyRepository = apiKeyRepository;
        this.exemptionRepository = exemptionRepository;
    }

    @Override
    public boolean isRequestAllowed(String keyValue) {
        ApiKey apiKey = apiKeyRepository.findByKeyValue(keyValue)
                .orElseThrow(() -> new RuntimeException("API Key not found"));

        // Rule: Check if key is active [cite: 642]
        if (!apiKey.getActive()) return false;

        // Rule: Check for active exemptions [cite: 629]
        boolean hasExemption = exemptionRepository.existsByApiKeyAndValidUntilAfter(apiKey, LocalDateTime.now());
        if (hasExemption) return true;

        // Rule: Enforce daily limit [cite: 638]
        LocalDateTime startOfDay = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
        long currentUsage = usageLogRepository.countByApiKeyAndTimestampAfter(apiKey, startOfDay);

        return currentUsage < apiKey.getPlan().getDailyLimit();
    }
}