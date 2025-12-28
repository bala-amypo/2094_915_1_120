package com.example.demo.model;

import java.sql.Timestamp;

public class KeyExemption {

    private Long id;
    private ApiKey apiKey;
    private String notes;
    private boolean unlimitedAccess;
    private Integer allowedExtraRequests;
    private Timestamp createdAt;

    public KeyExemption() {}

    public KeyExemption(ApiKey apiKey, String notes, boolean unlimitedAccess,
                        Integer allowedExtraRequests, Timestamp createdAt) {
        this.apiKey = apiKey;
        this.notes = notes;
        this.unlimitedAccess = unlimitedAccess;
        this.allowedExtraRequests = allowedExtraRequests;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public String getNotes() { return notes; }
    public boolean getUnlimitedAccess() { return unlimitedAccess; }

    public void setNotes(String notes) { this.notes = notes; }
    public void setUnlimitedAccess(boolean unlimitedAccess) {
        this.unlimitedAccess = unlimitedAccess;
    }
}
