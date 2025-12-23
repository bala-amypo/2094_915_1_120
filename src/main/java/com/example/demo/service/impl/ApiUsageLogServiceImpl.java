package com.example.demo.service.impl;

import com.example.demo.dto.ApiUsageLogDto;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ApiKey;
import com.example.demo.model.ApiUsageLog;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.ApiUsageLogRepository;
import com.example.demo.service.ApiUsageLogService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApiUsageLogServiceImpl implements ApiUsageLogService {
    private final ApiUsageLogRepository apiUsageLogRepository;
    private final ApiKeyRepository apiKeyRepository;

    public ApiUsageLogServiceImpl(ApiUsageLogRepository apiUsageLogRepository, ApiKeyRepository apiKeyRepository) {
        this.apiUsageLogRepository = apiUsageLogRepository;
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    public ApiUsageLogDto logUsage(ApiUsageLogDto logDto) {
        if (logDto.getTimestamp() == null || logDto.getTimestamp().after(new Timestamp(System.currentTimeMillis()))) {
            throw new BadRequestException("Timestamp cannot be in the future");
        }
        ApiKey apiKey = apiKeyRepository.findById(logDto.getApiKeyId())
            .orElseThrow(() -> new ResourceNotFoundException("API key not found"));
        if (!apiKey.getActive()) {
            throw new BadRequestException("API key is inactive");
        }
        ApiUsageLog log = new ApiUsageLog(apiKey, logDto.getEndpoint(), logDto.getTimestamp());
        apiUsageLogRepository.save(log);

        ApiUsageLogDto dto = new ApiUsageLogDto();
        dto.setId(log.getId());
        dto.setApiKeyId(apiKey.getId());
        dto.setEndpoint(log.getEndpoint());
        dto.setTimestamp(log.getTimestamp());
        return dto;
    }

    @Override
    public List<ApiUsageLogDto> getUsageForApiKey(Long keyId) {
        return apiUsageLogRepository.findByApiKey_Id(keyId).stream().map(log -> {
            ApiUsageLogDto dto = new ApiUsageLogDto();
            dto.setId(log.getId());
            dto.setApiKeyId(log.getApiKey().getId());
            dto.setEndpoint(log.getEndpoint());
            dto.setTimestamp(log.getTimestamp());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<ApiUsageLogDto> getUsageForToday(Long keyId) {
        LocalDate today = LocalDate.now();
        Instant start = today.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant end = today.plusDays(1).atStartOfDay(ZoneId.systemDefault()).minusMillis(1).toInstant();
        List<ApiUsageLog> logs = apiUsageLogRepository.findForKeyBetween(keyId, start, end);
        return logs.stream().map(log -> {
            ApiUsageLogDto dto = new ApiUsageLogDto();
            dto.setId(log.getId());
            dto.setApiKeyId(log.getApiKey().getId());
            dto.setEndpoint(log.getEndpoint());
            dto.setTimestamp(log.getTimestamp());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public Long countRequestsToday(Long keyId) {
        LocalDate today = LocalDate.now();
        Instant start = today.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant end = today.plusDays(1).atStartOfDay(ZoneId.systemDefault()).minusMillis(1).toInstant();
        return apiUsageLogRepository.countForKeyBetween(keyId, start, end);
    }
}
