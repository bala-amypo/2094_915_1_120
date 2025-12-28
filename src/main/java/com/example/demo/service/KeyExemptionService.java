package com.example.demo.service;
import com.example.demo.model.*;

public interface KeyExemptionService {
    KeyExemption createExemption(KeyExemption ex);
    KeyExemption getExemptionByKey(Long keyId);
}
