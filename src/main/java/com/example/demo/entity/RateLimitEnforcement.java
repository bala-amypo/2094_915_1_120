package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import java.time.LocalDateTime;

@Entity
@Table(name = "rate_limit_enforcements")
public class RateLimitEnforcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "api_key_id", nullable = false)
    private ApiKey apiKey; // Fixes: setApiKey

    @Column(nullable = false)
    private LocalDateTime blockedAt; // Use LocalDateTime per SRS 

    @Column(nullable = false)
    @Min(value = 1, message = "Limit exceeded must be at least 1")
    private Integer limitExceededBy; // Fixes: setLimitExceededBy

    @Column(columnDefinition = "TEXT")
    private String message; // Fixes: setMessage

    // No-arg constructor required by JPA [cite: 930]
    public RateLimitEnforcement() {}

    // Getters and Setters (REQUIRED to fix compilation)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public ApiKey getApiKey() { return apiKey; }
    public void setApiKey(ApiKey apiKey) { this.apiKey = apiKey; }

    public LocalDateTime getBlockedAt() { return blockedAt; }
    public void setBlockedAt(LocalDateTime blockedAt) { this.blockedAt = blockedAt; }

    public Integer getLimitExceededBy() { return limitExceededBy; }
    public void setLimitExceededBy(Integer limitExceededBy) { this.limitExceededBy = limitExceededBy; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}