package com.example.demo.repository;

import com.example.demo.entity.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface ApiKeyRepository extends JpaRepository<ApiKey, Long> {
    Optional<ApiKey> findByKeyValue(String keyValue);
    List<ApiKey> findByOwnerId(Long ownerId);
    List<ApiKey> findByActive(Boolean active);
    List<ApiKey> findByPlan_Id(Long planId);
}
