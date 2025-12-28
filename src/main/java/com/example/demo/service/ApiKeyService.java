package com.example.demo.service;

import com.example.demo.model.ApiKey;
import java.util.List;

public interface ApiKeyService {

    ApiKey createApiKey(ApiKey apiKey);

    ApiKey updateApiKey(Long id, ApiKey apiKey);

    ApiKey getApiKeyById(Long id);

    ApiKey getApiKeyByValue(String key);

    List<ApiKey> getAllApiKeys();

    void revokeApiKey(Long id);
}
