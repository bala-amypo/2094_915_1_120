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

    private RateLimitEnforcementDto mapToDto(RateLimitEnforcement enforcement) {
        RateLimitEnforcementDto dto = new RateLimitEnforcementDto();
        dto.setId(enforcement.getId()); // Fixes "cannot find symbol getId()"
        dto.setApiKeyId(enforcement.getApiKey().getId());
        dto.setBlockedAt(enforcement.getBlockedAt()); // Fixes "cannot find symbol getBlockedAt()"
        dto.setLimitExceededBy(enforcement.getLimitExceededBy());
        dto.setMessage(enforcement.getMessage());
        return dto;
    } 

    @Override
public List<RateLimitEnforcementDto> getEnforcementsForKey(Long keyId) {
    // Fetch from repository [cite: 1302, 1542]
    List<RateLimitEnforcement> enforcements = rateLimitEnforcementRepository.findByApiKey_Id(keyId);
    
    return enforcements.stream().map(enforcement -> {
        RateLimitEnforcementDto dto = new RateLimitEnforcementDto();
        dto.setId(enforcement.getId());
        dto.setApiKeyId(enforcement.getApiKey().getId());
        dto.setBlockedAt(enforcement.getBlockedAt()); // Matches LocalDateTime [cite: 1491]
        dto.setLimitExceededBy(enforcement.getLimitExceededBy());
        dto.setMessage(enforcement.getMessage());
        return dto;
    }).collect(Collectors.toList());
}
}