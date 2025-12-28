package com.example.demo.repository;

import com.example.demo.entity.QuotaPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface QuotaPlanRepository extends JpaRepository<QuotaPlan, Long> {
    Optional<QuotaPlan> findByPlanName(String planName);
    List<QuotaPlan> findByActive(Boolean active);
}
