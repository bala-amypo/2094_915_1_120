package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
public class ApiUsageLog {

    @Id 
    @GeneratedValue
    private Long id;

    @ManyToOne
    private ApiKey apiKey;

    private String endpoint;
    private Instant timestamp;

    public ApiKey getApiKey() { return apiKey; }
    public void setApiKey(ApiKey apiKey) { this.apiKey = apiKey; }

    public String getEndpoint() { return endpoint; }
    public void setEndpoint(String endpoint) { this.endpoint = endpoint; }

    public Instant getTimestamp() { return timestamp; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }
}
