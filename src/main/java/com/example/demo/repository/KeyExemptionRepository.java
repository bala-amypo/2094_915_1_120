package com.example.demo.repository;

import com.example.demo.entity.KeyExemption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.List;

public interface KeyExemptionRepository extends JpaRepository<KeyExemption, Long> {
    Optional<KeyExemption> findByApiKey_Id(Long id);
    List<KeyExemption> findByValidUntilBefore(Timestamp date);
}
