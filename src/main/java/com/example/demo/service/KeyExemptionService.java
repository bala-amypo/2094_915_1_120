package com.example.demo.service;

import com.example.demo.model.KeyExemption;
import java.util.List;

public interface KeyExemptionService {

    KeyExemption createExemption(KeyExemption exemption);

    KeyExemption updateExemption(Long id, KeyExemption exemption);

    KeyExemption getExemptionByKey(Long apiKeyId);

    List<KeyExemption> getAllExemptions();
}
