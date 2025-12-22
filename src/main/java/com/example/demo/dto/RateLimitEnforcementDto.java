package com.example.demo.dto;

import java.time.LocalDateTime;

public class RateLimitEnforcementDto {
    private Long id;
    private Long apiKeyId; // To represent the associated API key [cite: 235]
    private LocalDateTime blockedAt;
    private Integer limitExceededBy;
    private String message;

    // Standard no-arg and parameterized constructors [cite: 240, 241]
    public RateLimitEnforcementDto() {}

    public RateLimitEnforcementDto(Long apiKeyId, LocalDateTime blockedAt, Integer limitExceededBy, String message) {
        this.apiKeyId = apiKeyId;
        this.blockedAt = blockedAt;
        this.limitExceededBy = limitExceededBy;
        this.message = message;
    }

    // Add Getters and Setters for all fields
}