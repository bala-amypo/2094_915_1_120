package com.example.demo.service.impl;

import com.example.demo.repository.RateLimitEnforcementRepository;
import com.example.demo.repository.ApiKeyRepository;

public class RateLimitEnforcementServiceImpl implements RateLimitEnforcementService {
    private final RateLimitEnforcementRepository repository;
    private final ApiKeyRepository apiKeyRepository;

    // Must have this constructor
    public RateLimitEnforcementServiceImpl(RateLimitEnforcementRepository repository, 
                                           ApiKeyRepository apiKeyRepository) {
        this.repository = repository;
        this.apiKeyRepository = apiKeyRepository;
    }
}