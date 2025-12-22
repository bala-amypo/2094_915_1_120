package com.example.demo.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "rate_limit_enforcement")
public class RateLimitEnforcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "api_key_id", nullable = false)
    private ApiKey apiKey;

    @Column(nullable = false)
    private Timestamp blockedAt;

    @Column(nullable = false)
    private Integer limitExceededBy;

    private String message;

    public Long getId() { return id; }
    public Timestamp getBlockedAt() { return blockedAt; }
    public ApiKey getApiKey() { return apiKey; }
    public Integer getLimitExceededBy() { return limitExceededBy; }
    public String getMessage() { return message; }
    
}