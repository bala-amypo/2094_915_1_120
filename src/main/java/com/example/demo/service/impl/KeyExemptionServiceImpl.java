package com.example.demo.service.impl;

import com.example.demo.entity.KeyExemption;
import com.example.demo.repository.KeyExemptionRepository;
import com.example.demo.service.KeyExemptionService;

import java.util.List;

public class KeyExemptionServiceImpl implements KeyExemptionService {

    private final KeyExemptionRepository repository;

    public KeyExemptionServiceImpl(KeyExemptionRepository repository) {
        this.repository = repository;
    }

    @Override
    public KeyExemption createExemption(KeyExemption exemption) {
        return repository.save(exemption);
    }

    @Override
    public KeyExemption getExemptionByKey(Long apiKeyId) {
        return repository.findAll()
                .stream()
                .filter(e -> e.getApiKey() != null &&
                             e.getApiKey().getId().equals(apiKeyId))
                .findFirst()
                .orElse(null);
    }

    // ❗ NO @Override here (not in interface)
    public List<KeyExemption> getAllExemptions() {
        return repository.findAll();
    }
}
