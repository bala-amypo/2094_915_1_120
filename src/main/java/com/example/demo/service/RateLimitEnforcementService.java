package com.example.demo.service;

import com.example.demo.model.RateLimitEnforcement;
import java.util.List;

public interface RateLimitEnforcementService {

    RateLimitEnforcement enforce(RateLimitEnforcement enforcement);

    List<RateLimitEnforcement> getAll();
}
