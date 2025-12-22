package com.example.demo.service.impl;

import com.example.demo.dto.RateLimitEnforcementDto;
import com.example.demo.entity.ApiKey;
import com.example.demo.entity.RateLimitEnforcement;
import com.example.demo.exception.BadRequestException; // Ensure this exists
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.RateLimitEnforcementRepository;
import com.example.demo.service.RateLimitEnforcementService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RateLimitEnforcementServiceImpl implements RateLimitEnforcementService {

    private final RateLimitEnforcementRepository enforcementRepo;
    private final ApiKeyRepository apiKeyRepository; // Corrected variable name

    // Constructor Injection (Mandatory per SRS)
    public RateLimitEnforcementServiceImpl(RateLimitEnforcementRepository enforcementRepo, 
                                           ApiKeyRepository apiKeyRepository) {
        this.enforcementRepo = enforcementRepo;
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    public RateLimitEnforcementDto createEnforcement(RateLimitEnforcementDto dto) {
        // Validation Rule: Must be at least 1
        if (dto.getLimitExceededBy() == null || dto.getLimitExceededBy() < 1) {
            throw new BadRequestException("Limit exceeded must be at least 1");
        }

        ApiKey apiKey = apiKeyRepository.findById(dto.getApiKeyId())
                .orElseThrow(() -> new ResourceNotFoundException("API Key not found"));

        RateLimitEnforcement entity = new RateLimitEnforcement();
        entity.setApiKey(apiKey);
        entity.setLimitExceededBy(dto.getLimitExceededBy());
        entity.setBlockedAt(dto.getBlockedAt()); // Assuming both are LocalDateTime
        entity.setMessage(dto.getMessage());

        RateLimitEnforcement saved = enforcementRepo.save(entity);
        return mapToDto(saved);
    }

    private RateLimitEnforcementDto mapToDto(RateLimitEnforcement entity) {
        RateLimitEnforcementDto dto = new RateLimitEnforcementDto();
        dto.setId(entity.getId());
        dto.setApiKeyId(entity.getApiKey().getId());
        dto.setLimitExceededBy(entity.getLimitExceededBy());
        dto.setMessage(entity.getMessage());
        dto.setBlockedAt(entity.getBlockedAt()); // No conversion needed if both are LocalDateTime
        return dto;
    }

    @Override
    public List<RateLimitEnforcementDto> getEnforcementsForKey(Long keyId) {
        return enforcementRepo.findByApiKey_Id(keyId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public RateLimitEnforcementDto getEnforcementById(Long id) {
        return enforcementRepo.findById(id)
                .map(this::mapToDto)
                .orElseThrow(() -> new ResourceNotFoundException("Enforcement event not found"));
    }
}