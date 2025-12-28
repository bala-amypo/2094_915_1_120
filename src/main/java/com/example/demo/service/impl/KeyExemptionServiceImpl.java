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
    public KeyExemption save(KeyExemption exemption) {
        return repository.save(exemption);
    }

    @Override
    public List<KeyExemption> findAll() {
        return repository.findAll();
    }
}
