package com.example.demo.service.impl;

import com.example.demo.dto.RateLimitEnforcementDto;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.entity.ApiKey;
import com.example.demo.entity.RateLimitEnforcement;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.RateLimitEnforcementRepository;
import com.example.demo.service.RateLimitEnforcementService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RateLimitEnforcementServiceImpl implements RateLimitEnforcementService {
    private final RateLimitEnforcementRepository enforcementRepository;
    private final ApiKeyRepository apiKeyRepository;

    public RateLimitEnforcementServiceImpl(RateLimitEnforcementRepository enforcementRepository,
                                           ApiKeyRepository apiKeyRepository) {
        this.enforcementRepository = enforcementRepository;
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    public RateLimitEnforcementDto createEnforcement(RateLimitEnforcementDto dto) {
        if (dto.getLimitExceededBy() == null || dto.getLimitExceededBy() < 1) {
            throw new BadRequestException("Limit exceeded must be at least 1");
        }
        ApiKey apiKey = apiKeyRepository.findById(dto.getApiKeyId())
            .orElseThrow(() -> new ResourceNotFoundException("API key not found"));
        if (!apiKey.getActive()) {
            throw new BadRequestException("Cannot enforce on inactive API key");
        }
        RateLimitEnforcement enforcement = new RateLimitEnforcement(
            apiKey,
            dto.getBlockedAt(),
            dto.getLimitExceededBy(),
            dto.getMessage()
        );
        enforcementRepository.save(enforcement);

        RateLimitEnforcementDto response = new RateLimitEnforcementDto();
        response.setId(enforcement.getId());
        response.setApiKeyId(apiKey.getId());
        response.setBlockedAt(enforcement.getBlockedAt());
        response.setLimitExceededBy(enforcement.getLimitExceededBy());
        response.setMessage(enforcement.getMessage());
        return response;
    }

    @Override
    public RateLimitEnforcementDto getEnforcementById(Long id) {
        RateLimitEnforcement enforcement = enforcementRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Enforcement event not found"));
        RateLimitEnforcementDto dto = new RateLimitEnforcementDto();
        dto.setId(enforcement.getId());
        dto.setApiKeyId(enforcement.getApiKey().getId());
        dto.setBlockedAt(enforcement.getBlockedAt());
        dto.setLimitExceededBy(enforcement.getLimitExceededBy());
        dto.setMessage(enforcement.getMessage());
        return dto;
    }

    @Override
    public List<RateLimitEnforcementDto> getEnforcementsForKey(Long keyId) {
        return enforcementRepository.findByApiKey_Id(keyId).stream().map(enf -> {
            RateLimitEnforcementDto dto = new RateLimitEnforcementDto();
            dto.setId(enf.getId());
            dto.setApiKeyId(enf.getApiKey().getId());
            dto.setBlockedAt(enf.getBlockedAt());
            dto.setLimitExceededBy(enf.getLimitExceededBy());
            dto.setMessage(enf.getMessage());
            return dto;
        }).collect(Collectors.toList());
    }
}
