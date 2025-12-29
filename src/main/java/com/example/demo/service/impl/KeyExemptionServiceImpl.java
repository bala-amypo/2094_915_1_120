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
    public boolean isExempted(String apiKey) {
        return repository.findByApiKey(apiKey)
                .map(KeyExemption::isExempted)
                .orElse(false);
    }

    @Override
    public KeyExemption exemptKey(String apiKey) {
        KeyExemption exemption = repository.findByApiKey(apiKey)
                .orElseGet(() -> {
                    KeyExemption ke = new KeyExemption();
                    ke.setApiKey(apiKey);
                    return ke;
                });

        exemption.setExempted(true);
        return repository.save(exemption);
    }

    @Override
    public void removeExemption(String apiKey) {
        repository.findByApiKey(apiKey).ifPresent(repository::delete);
    }
}
