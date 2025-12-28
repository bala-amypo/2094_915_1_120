package com.example.demo.service.impl;

import com.example.demo.dto.QuotaPlanDto;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.entity.QuotaPlan;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.QuotaPlanRepository;
import com.example.demo.service.QuotaPlanService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuotaPlanServiceImpl implements QuotaPlanService {
    private final QuotaPlanRepository quotaPlanRepository;
    private final ApiKeyRepository apiKeyRepository; // for validation

    public QuotaPlanServiceImpl(QuotaPlanRepository quotaPlanRepository,
                                ApiKeyRepository apiKeyRepository) {
        this.quotaPlanRepository = quotaPlanRepository;
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    public QuotaPlanDto createQuotaPlan(QuotaPlanDto planDto) {
        if (planDto.getDailyLimit() == null || planDto.getDailyLimit() <= 0) {
            throw new BadRequestException("Daily limit must be greater than 0");
        }
        quotaPlanRepository.findByPlanName(planDto.getPlanName()).ifPresent(p -> {
            throw new BadRequestException("Plan name must be unique");
        });
        QuotaPlan plan = new QuotaPlan(planDto.getPlanName(), planDto.getDailyLimit(), planDto.getDescription());
        plan.setActive(planDto.getActive() != null ? planDto.getActive() : true);
        quotaPlanRepository.save(plan);

        QuotaPlanDto dto = new QuotaPlanDto();
        dto.setId(plan.getId());
        dto.setPlanName(plan.getPlanName());
        dto.setDailyLimit(plan.getDailyLimit());
        dto.setDescription(plan.getDescription());
        dto.setActive(plan.getActive());
        return dto;
    }

    @Override
    public QuotaPlanDto updateQuotaPlan(Long id, QuotaPlanDto planDto) {
        QuotaPlan plan = quotaPlanRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Quota plan not found"));
        if (planDto.getDailyLimit() != null && planDto.getDailyLimit() <= 0) {
            throw new BadRequestException("Daily limit must be greater than 0");
        }
        if (planDto.getPlanName() != null && !planDto.getPlanName().equals(plan.getPlanName())) {
            quotaPlanRepository.findByPlanName(planDto.getPlanName()).ifPresent(p -> {
                throw new BadRequestException("Plan name must be unique");
            });
            plan.setPlanName(planDto.getPlanName());
        }
        if (planDto.getDailyLimit() != null) {
            plan.setDailyLimit(planDto.getDailyLimit());
        }
        plan.setDescription(planDto.getDescription());
        if (planDto.getActive() != null) {
            plan.setActive(planDto.getActive());
        }
        quotaPlanRepository.save(plan);

        QuotaPlanDto dto = new QuotaPlanDto();
        dto.setId(plan.getId());
        dto.setPlanName(plan.getPlanName());
        dto.setDailyLimit(plan.getDailyLimit());
        dto.setDescription(plan.getDescription());
        dto.setActive(plan.getActive());
        return dto;
    }

    @Override
    public QuotaPlanDto getQuotaPlanById(Long id) {
        QuotaPlan plan = quotaPlanRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Quota plan not found"));
        QuotaPlanDto dto = new QuotaPlanDto();
        dto.setId(plan.getId());
        dto.setPlanName(plan.getPlanName());
        dto.setDailyLimit(plan.getDailyLimit());
        dto.setDescription(plan.getDescription());
        dto.setActive(plan.getActive());
        return dto;
    }

    @Override
    public List<QuotaPlanDto> getAllPlans() {
        return quotaPlanRepository.findAll().stream().map(plan -> {
            QuotaPlanDto dto = new QuotaPlanDto();
            dto.setId(plan.getId());
            dto.setPlanName(plan.getPlanName());
            dto.setDailyLimit(plan.getDailyLimit());
            dto.setDescription(plan.getDescription());
            dto.setActive(plan.getActive());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public void deactivateQuotaPlan(Long id) {
        QuotaPlan plan = quotaPlanRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Quota plan not found"));
        // Check if active keys exist for this plan
        boolean hasActiveKeys = apiKeyRepository.findByPlan_Id(id).stream()
            .anyMatch(ApiKey -> ApiKey.getActive());
        if (hasActiveKeys) {
            throw new BadRequestException("Cannot deactivate plan with active API keys");
        }
        plan.setActive(false);
        quotaPlanRepository.save(plan);
    }
}
