package com.example.demo.service.impl;

import com.example.demo.dto.ApiKeyDto;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ApiKey;
import com.example.demo.model.QuotaPlan;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.QuotaPlanRepository;
import com.example.demo.service.ApiKeyService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApiKeyServiceImpl implements ApiKeyService {
    private final ApiKeyRepository apiKeyRepository;
    private final QuotaPlanRepository quotaPlanRepository;

    public ApiKeyServiceImpl(ApiKeyRepository apiKeyRepository, QuotaPlanRepository quotaPlanRepository) {
        this.apiKeyRepository = apiKeyRepository;
        this.quotaPlanRepository = quotaPlanRepository;
    }

    @Override
    public ApiKeyDto createApiKey(ApiKeyDto keyDto) {
        // Check if quota plan exists and is active
        QuotaPlan plan = quotaPlanRepository.findById(keyDto.getPlanId())
            .orElseThrow(() -> new ResourceNotFoundException("QuotaPlan not found"));
        if (!plan.getActive()) {
            throw new BadRequestException("Cannot assign API key to an inactive plan");
        }
        // Check unique keyValue
        apiKeyRepository.findByKeyValue(keyDto.getKeyValue()).ifPresent(k -> {
            throw new BadRequestException("API key value must be unique");
        });
        // Create and save API key
        ApiKey apiKey = new ApiKey(keyDto.getKeyValue(), keyDto.getOwnerId(), plan);
        apiKey.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        apiKey.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        apiKey.setActive(true);
        apiKeyRepository.save(apiKey);

        // Map to DTO
        ApiKeyDto dto = new ApiKeyDto();
        dto.setId(apiKey.getId());
        dto.setKeyValue(apiKey.getKeyValue());
        dto.setOwnerId(apiKey.getOwnerId());
        dto.setPlanId(plan.getId());
        dto.setActive(apiKey.getActive());
        dto.setCreatedAt(apiKey.getCreatedAt());
        dto.setUpdatedAt(apiKey.getUpdatedAt());
        return dto;
    }

    @Override
    public ApiKeyDto updateApiKey(Long id, ApiKeyDto keyDto) {
        ApiKey apiKey = apiKeyRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("API key not found"));
        if (!apiKey.getActive()) {
            throw new BadRequestException("Cannot update an inactive API key");
        }
        // Update keyValue if changed and unique
        String newValue = keyDto.getKeyValue();
        if (newValue != null && !newValue.equals(apiKey.getKeyValue())) {
            apiKeyRepository.findByKeyValue(newValue).ifPresent(k -> {
                throw new BadRequestException("API key value must be unique");
            });
            apiKey.setKeyValue(newValue);
        }
        // Update ownerId
        apiKey.setOwnerId(keyDto.getOwnerId());
        // Update plan if changed
        if (keyDto.getPlanId() != null && !keyDto.getPlanId().equals(apiKey.getPlan().getId())) {
            QuotaPlan plan = quotaPlanRepository.findById(keyDto.getPlanId())
                .orElseThrow(() -> new ResourceNotFoundException("QuotaPlan not found"));
            if (!plan.getActive()) {
                throw new BadRequestException("Cannot assign API key to an inactive plan");
            }
            apiKey.setPlan(plan);
        }
        // Update timestamp
        apiKey.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        apiKeyRepository.save(apiKey);

        // Map to DTO
        ApiKeyDto dto = new ApiKeyDto();
        dto.setId(apiKey.getId());
        dto.setKeyValue(apiKey.getKeyValue());
        dto.setOwnerId(apiKey.getOwnerId());
        dto.setPlanId(apiKey.getPlan().getId());
        dto.setActive(apiKey.getActive());
        dto.setCreatedAt(apiKey.getCreatedAt());
        dto.setUpdatedAt(apiKey.getUpdatedAt());
        return dto;
    }

    @Override
    public ApiKeyDto getApiKeyById(Long id) {
        ApiKey apiKey = apiKeyRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("API key not found"));
        ApiKeyDto dto = new ApiKeyDto();
        dto.setId(apiKey.getId());
        dto.setKeyValue(apiKey.getKeyValue());
        dto.setOwnerId(apiKey.getOwnerId());
        dto.setPlanId(apiKey.getPlan().getId());
        dto.setActive(apiKey.getActive());
        dto.setCreatedAt(apiKey.getCreatedAt());
        dto.setUpdatedAt(apiKey.getUpdatedAt());
        return dto;
    }

    @Override
    public ApiKeyDto getApiKeyByValue(String keyValue) {
        ApiKey apiKey = apiKeyRepository.findByKeyValue(keyValue)
            .orElseThrow(() -> new ResourceNotFoundException("API key not found"));
        ApiKeyDto dto = new ApiKeyDto();
        dto.setId(apiKey.getId());
        dto.setKeyValue(apiKey.getKeyValue());
        dto.setOwnerId(apiKey.getOwnerId());
        dto.setPlanId(apiKey.getPlan().getId());
        dto.setActive(apiKey.getActive());
        dto.setCreatedAt(apiKey.getCreatedAt());
        dto.setUpdatedAt(apiKey.getUpdatedAt());
        return dto;
    }

    @Override
    public List<ApiKeyDto> getAllApiKeys() {
        return apiKeyRepository.findAll().stream().map(apiKey -> {
            ApiKeyDto dto = new ApiKeyDto();
            dto.setId(apiKey.getId());
            dto.setKeyValue(apiKey.getKeyValue());
            dto.setOwnerId(apiKey.getOwnerId());
            dto.setPlanId(apiKey.getPlan().getId());
            dto.setActive(apiKey.getActive());
            dto.setCreatedAt(apiKey.getCreatedAt());
            dto.setUpdatedAt(apiKey.getUpdatedAt());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public void deactivateApiKey(Long id) {
        ApiKey apiKey = apiKeyRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("API key not found"));
        if (!apiKey.getActive()) {
            throw new BadRequestException("API key is already inactive");
        }
        apiKey.setActive(false);
        apiKeyRepository.save(apiKey);
    }
}
