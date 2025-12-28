package com.example.demo.service;

import com.example.demo.model.QuotaPlan;
import java.util.List;

public interface QuotaPlanService {

    QuotaPlan createPlan(QuotaPlan plan);

    QuotaPlan updatePlan(Long id, QuotaPlan plan);

    QuotaPlan getQuotaPlanById(Long id);

    List<QuotaPlan> getAllPlans();
}
