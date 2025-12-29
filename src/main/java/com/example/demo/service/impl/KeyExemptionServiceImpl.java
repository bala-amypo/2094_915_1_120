package com.example.demo.service.impl;

import com.example.demo.entity.ApiKey;
import com.example.demo.entity.KeyExemption;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.KeyExemptionRepository;
import com.example.demo.service.KeyExemptionService;
import org.springframework.stereotype.Service;

@Service
public class KeyExemptionServiceImpl implements KeyExemptionService {

    private final KeyExemptionRepository exemptionRepository;
    private final ApiKeyRepository apiKeyRepository;

    public KeyExemptionServiceImpl(
            KeyExemptionRepository exemptionRepository,
            ApiKeyRepository apiKeyRepository) {
        this.exemptionRepository = exemptionRepository;
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    public KeyExemption getExemptionByKey(Long apiKeyId) {
        return exemptionRepository.findByApiKeyId(apiKeyId).orElse(null);
    }

    @Override
    public boolean isExempted(Long apiKeyId) {
        return exemptionRepository.findByApiKeyId(apiKeyId)
                .map(KeyExemption::isExempted)
                .orElse(false);
    }

    @Override
    public KeyExemption exemptKey(Long apiKeyId) {
        ApiKey apiKey = apiKeyRepository.findById(apiKeyId)
                .orElseThrow(() -> new RuntimeException("API Key not found"));

        KeyExemption exemption = exemptionRepository.findByApiKeyId(apiKeyId)
                .orElseGet(() -> {
                    KeyExemption ke = new KeyExemption();
                    ke.setApiKey(apiKey);
                    return ke;
                });

        exemption.setExempted(true);
        return exemptionRepository.save(exemption);
    }

    @Override
    public void removeExemption(Long apiKeyId) {
        exemptionRepository.findByApiKeyId(apiKeyId)
                .ifPresent(exemptionRepository::delete);
    }
}
