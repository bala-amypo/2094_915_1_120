package com.example.demo.model;

import java.sql.Timestamp;

public class KeyExemption {

    private Long id;
    private ApiKey apiKey;
    private Integer temporaryExtensionLimit;
    private Timestamp validUntil;

    public Long getId() { return id; }

    public ApiKey getApiKey() { return apiKey; }
    public void setApiKey(ApiKey apiKey) { this.apiKey = apiKey; }

    public Integer getTemporaryExtensionLimit() {
        return temporaryExtensionLimit;
    }
    public void setTemporaryExtensionLimit(Integer temporaryExtensionLimit) {
        this.temporaryExtensionLimit = temporaryExtensionLimit;
    }

    public Timestamp getValidUntil() { return validUntil; }
    public void setValidUntil(Timestamp validUntil) {
        this.validUntil = validUntil;
    }
}
