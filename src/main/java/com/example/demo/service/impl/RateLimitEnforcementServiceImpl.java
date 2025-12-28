package com.example.demo.service.impl;

import com.example.demo.entity.RateLimitEnforcement;
import com.example.demo.repository.RateLimitEnforcementRepository;
import com.example.demo.service.RateLimitEnforcementService;

import java.util.List;
import java.util.stream.Collectors;

public class RateLimitEnforcementServiceImpl implements RateLimitEnforcementService {

    private final RateLimitEnforcementRepository repository;

    public RateLimitEnforcementServiceImpl(RateLimitEnforcementRepository repository) {
        this.repository = repository;
    }

    @Override
    public RateLimitEnforcement createEnforcement(RateLimitEnforcement enforcement) {
        return repository.save(enforcement);
    }

    @Override
    public RateLimitEnforcement getEnforcementById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<RateLimitEnforcement> getEnforcementsForKey(Long apiKeyId) {
        return repository.findAll()
                .stream()
                .filter(e -> e.getApiKey() != null &&
                             e.getApiKey().getId().equals(apiKeyId))
                .collect(Collectors.toList());
    }
}
