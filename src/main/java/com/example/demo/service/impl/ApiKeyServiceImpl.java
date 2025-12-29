package com.example.demo.service.impl;

import com.example.demo.entity.ApiKey;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.service.ApiKeyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApiKeyServiceImpl implements ApiKeyService {

    private final ApiKeyRepository repository;

    @Override
    public List<ApiKey> findAll() {
        return repository.findAll();
    }
}
