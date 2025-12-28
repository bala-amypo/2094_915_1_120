package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.sql.Timestamp;

public class ApiUsageLogDto {
    private Long id;

    @NotNull(message = "API Key ID is required")
    private Long apiKeyId;

    @NotBlank(message = "Endpoint is required")
    private String endpoint;

    @NotNull(message = "Timestamp is required")
    private Timestamp timestamp;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getApiKeyId() { return apiKeyId; }
    public void setApiKeyId(Long apiKeyId) { this.apiKeyId = apiKeyId; }
    public String getEndpoint() { return endpoint; }
    public void setEndpoint(String endpoint) { this.endpoint = endpoint; }
    public Timestamp getTimestamp() { return timestamp; }
    public void setTimestamp(Timestamp timestamp) { this.timestamp = timestamp; }
}
