package com.example.demo.repository;

import com.example.demo.entity.RateLimitEnforcement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface RateLimitEnforcementRepository extends JpaRepository<RateLimitEnforcement, Long> {
    List<RateLimitEnforcement> findByApiKey_Id(Long id);
    List<RateLimitEnforcement> findByBlockedAtBetween(Timestamp start, Timestamp end);
}
