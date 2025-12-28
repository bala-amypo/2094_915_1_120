package com.example.demo.service.impl;

import com.example.demo.entity.QuotaPlan;
import com.example.demo.repository.QuotaPlanRepository;
import com.example.demo.service.QuotaPlanService;

import java.util.List;

public class QuotaPlanServiceImpl implements QuotaPlanService {

    private final QuotaPlanRepository repository;

    public QuotaPlanServiceImpl(QuotaPlanRepository repository) {
        this.repository = repository;
    }

    @Override
    public QuotaPlan save(QuotaPlan plan) {
        return repository.save(plan);
    }

    @Override
    public List<QuotaPlan> findAll() {
        return repository.findAll();
    }
}
