package com.example.demo.service.impl;

import com.example.demo.dto.KeyExemptionDto;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ApiKey;
import com.example.demo.model.KeyExemption;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.KeyExemptionRepository;
import com.example.demo.service.KeyExemptionService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class KeyExemptionServiceImpl implements KeyExemptionService {
    private final KeyExemptionRepository keyExemptionRepository;
    private final ApiKeyRepository apiKeyRepository;

    public KeyExemptionServiceImpl(KeyExemptionRepository keyExemptionRepository,
                                   ApiKeyRepository apiKeyRepository) {
        this.keyExemptionRepository = keyExemptionRepository;
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    public KeyExemptionDto createExemption(KeyExemptionDto dto) {
        ApiKey apiKey = apiKeyRepository.findById(dto.getApiKeyId())
            .orElseThrow(() -> new ResourceNotFoundException("API key not found"));
        if (!apiKey.getActive()) {
            throw new BadRequestException("Cannot create exemption for inactive API key");
        }
        // Validate exemption rules
        if (Boolean.TRUE.equals(dto.getUnlimitedAccess()) && dto.getTemporaryExtensionLimit() != null
                && dto.getTemporaryExtensionLimit() > 0) {
            throw new BadRequestException("Cannot have both unlimited access and temporary extension");
        }
        if (dto.getValidUntil().before(Timestamp.from(Instant.now()))) {
            throw new BadRequestException("ValidUntil must be in the future");
        }
        KeyExemption exemption = new KeyExemption(
            apiKey,
            dto.getNotes(),
            Boolean.TRUE.equals(dto.getUnlimitedAccess()),
            dto.getTemporaryExtensionLimit(),
            dto.getValidUntil()
        );
        keyExemptionRepository.save(exemption);

        KeyExemptionDto response = new KeyExemptionDto();
        response.setId(exemption.getId());
        response.setApiKeyId(apiKey.getId());
        response.setNotes(exemption.getNotes());
        response.setUnlimitedAccess(exemption.getUnlimitedAccess());
        response.setTemporaryExtensionLimit(exemption.getTemporaryExtensionLimit());
        response.setValidUntil(exemption.getValidUntil());
        return response;
    }

    @Override
    public KeyExemptionDto updateExemption(Long id, KeyExemptionDto dto) {
        KeyExemption exemption = keyExemptionRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("KeyExemption not found"));
        ApiKey apiKey = apiKeyRepository.findById(dto.getApiKeyId())
            .orElseThrow(() -> new ResourceNotFoundException("API key not found"));
        if (!apiKey.getActive()) {
            throw new BadRequestException("Cannot update exemption for inactive API key");
        }
        if (Boolean.TRUE.equals(dto.getUnlimitedAccess()) && dto.getTemporaryExtensionLimit() != null
                && dto.getTemporaryExtensionLimit() > 0) {
            throw new BadRequestException("Cannot have both unlimited access and temporary extension");
        }
        if (dto.getValidUntil().before(Timestamp.from(Instant.now()))) {
            throw new BadRequestException("ValidUntil must be in the future");
        }
        exemption.setApiKey(apiKey);
        exemption.setNotes(dto.getNotes());
        exemption.setUnlimitedAccess(Boolean.TRUE.equals(dto.getUnlimitedAccess()));
        exemption.setTemporaryExtensionLimit(dto.getTemporaryExtensionLimit());
        exemption.setValidUntil(dto.getValidUntil());
        keyExemptionRepository.save(exemption);

        KeyExemptionDto response = new KeyExemptionDto();
        response.setId(exemption.getId());
        response.setApiKeyId(apiKey.getId());
        response.setNotes(exemption.getNotes());
        response.setUnlimitedAccess(exemption.getUnlimitedAccess());
        response.setTemporaryExtensionLimit(exemption.getTemporaryExtensionLimit());
        response.setValidUntil(exemption.getValidUntil());
        return response;
    }

    @Override
    public KeyExemptionDto getExemptionByKey(Long apiKeyId) {
        KeyExemption exemption = keyExemptionRepository.findByApiKey_Id(apiKeyId)
            .orElseThrow(() -> new ResourceNotFoundException("KeyExemption not found for API key"));
        KeyExemptionDto dto = new KeyExemptionDto();
        dto.setId(exemption.getId());
        dto.setApiKeyId(exemption.getApiKey().getId());
        dto.setNotes(exemption.getNotes());
        dto.setUnlimitedAccess(exemption.getUnlimitedAccess());
        dto.setTemporaryExtensionLimit(exemption.getTemporaryExtensionLimit());
        dto.setValidUntil(exemption.getValidUntil());
        return dto;
    }

    @Override
    public List<KeyExemptionDto> getAllExemptions() {
        return keyExemptionRepository.findAll().stream().map(exemption -> {
            KeyExemptionDto dto = new KeyExemptionDto();
            dto.setId(exemption.getId());
            dto.setApiKeyId(exemption.getApiKey().getId());
            dto.setNotes(exemption.getNotes());
            dto.setUnlimitedAccess(exemption.getUnlimitedAccess());
            dto.setTemporaryExtensionLimit(exemption.getTemporaryExtensionLimit());
            dto.setValidUntil(exemption.getValidUntil());
            return dto;
        }).collect(Collectors.toList());
    }
}
