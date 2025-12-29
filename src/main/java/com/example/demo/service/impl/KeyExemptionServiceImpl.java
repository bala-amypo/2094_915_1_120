package com.example.demo.service.impl;

import com.example.demo.entity.KeyExemption;
import com.example.demo.repository.KeyExemptionRepository;
import com.example.demo.service.KeyExemptionService;
import org.springframework.stereotype.Service;

@Service
public class KeyExemptionServiceImpl implements KeyExemptionService {

    private final KeyExemptionRepository repository;

    public KeyExemptionServiceImpl(KeyExemptionRepository repository) {
        this.repository = repository;
    }

    @Override
    public KeyExemption getExemptionByKey(Long apiKeyId) {
        return repository.findByApiKeyId(apiKeyId).orElse(null);
    }

    @Override
    public void removeExemption(Long apiKeyId) {
        repository.findByApiKeyId(apiKeyId)
                  .ifPresent(repository::delete);
    }
}
