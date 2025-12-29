package com.example.demo.service;

import com.example.demo.entity.KeyExemption;

public interface KeyExemptionService {

    KeyExemption getExemptionByKey(Long apiKeyId);

    void removeExemption(Long apiKeyId);   
}
