package com.example.demo.service;

import com.example.demo.dto.KeyExemptionDto;
import java.util.List;

public interface KeyExemptionService {
    KeyExemptionDto createExemption(KeyExemptionDto exemptionDto);
    KeyExemptionDto updateExemption(Long id, KeyExemptionDto exemptionDto);
    KeyExemptionDto getExemptionByKey(Long apiKeyId);
    List<KeyExemptionDto> getAllExemptions();
}
