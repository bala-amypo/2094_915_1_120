package com.example.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.sql.Timestamp;

public class KeyExemptionDto {
    private Long id;

    @NotNull(message = "API Key ID is required")
    private Long apiKeyId;

    private String notes;

    private Boolean unlimitedAccess;

    @Min(value = 0, message = "Temporary extension must be >= 0")
    private Integer temporaryExtensionLimit;

    @NotNull(message = "Valid until date is required")
    private Timestamp validUntil;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getApiKeyId() { return apiKeyId; }
    public void setApiKeyId(Long apiKeyId) { this.apiKeyId = apiKeyId; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    public Boolean getUnlimitedAccess() { return unlimitedAccess; }
    public void setUnlimitedAccess(Boolean unlimitedAccess) { this.unlimitedAccess = unlimitedAccess; }
    public Integer getTemporaryExtensionLimit() { return temporaryExtensionLimit; }
    public void setTemporaryExtensionLimit(Integer temporaryExtensionLimit) { this.temporaryExtensionLimit = temporaryExtensionLimit; }
    public Timestamp getValidUntil() { return validUntil; }
    public void setValidUntil(Timestamp validUntil) { this.validUntil = validUntil; }
}
