package com.example.demo.service.impl;

import com.example.demo.dto.RateLimitEnforcementDto;
import com.example.demo.entity.RateLimitEnforcement;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.RateLimitEnforcementRepository;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.service.RateLimitEnforcementService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RateLimitEnforcementServiceImpl implements RateLimitEnforcementService {

    private final RateLimitEnforcementRepository rateLimitEnforcementRepository;
    private final ApiKeyRepository apiKeyRepository;

    // Required Constructor Injection [cite: 264]
    public RateLimitEnforcementServiceImpl(RateLimitEnforcementRepository rateLimitEnforcementRepository, 
                                           ApiKeyRepository apiKeyRepository) {
        this.rateLimitEnforcementRepository = rateLimitEnforcementRepository;
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    public RateLimitEnforcementDto getEnforcementById(Long id) {
        RateLimitEnforcement enforcement = rateLimitEnforcementRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Enforcement event not found"));
        return mapToDto(enforcement);
    }

    // Example of the mapping logic that was causing errors [cite: 395]
    private RateLimitEnforcementDto mapToDto(RateLimitEnforcement enforcement) {
        RateLimitEnforcementDto dto = new RateLimitEnforcementDto();
        dto.setId(enforcement.getId()); // Fixes "cannot find symbol getId()"
        dto.setApiKeyId(enforcement.getApiKey().getId());
        dto.setBlockedAt(enforcement.getBlockedAt()); // Fixes "cannot find symbol getBlockedAt()"
        dto.setLimitExceededBy(enforcement.getLimitExceededBy());
        dto.setMessage(enforcement.getMessage());
        return dto;
    }
    
    // Implement other required methods: createEnforcement and getEnforcementsForKey 
}