package com.example.demo.service.impl;

import com.example.demo.dto.RateLimitEnforcementDto;
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
    public List<RateLimitEnforcementDto> getEnforcementsForKey(Long keyId) {
        return rateLimitEnforcementRepository.findByApiKey_Id(keyId)
                .stream()
                .map(enforcement -> {
                    RateLimitEnforcementDto dto = new RateLimitEnforcementDto();
                    dto.setId(enforcement.getId());
                    dto.setApiKeyId(enforcement.getApiKey().getId());
                    dto.setBlockedAt(enforcement.getBlockedAt().toLocalDateTime());
                    dto.setLimitExceededBy(enforcement.getLimitExceededBy());
                    dto.setMessage(enforcement.getMessage());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}