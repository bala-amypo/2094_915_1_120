package com.example.demo.service.impl;

import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.service.ApiKeyService;
import com.example.demo.entity.ApiKey;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ApiKeyServiceImpl implements ApiKeyService {

    private final ApiKeyRepository repo;

    public ApiKeyServiceImpl(ApiKeyRepository repo) {
        this.repo = repo;
    }

    public List<ApiKey> getAllKeys() {
        return repo.findAll();
    }
}
