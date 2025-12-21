package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class ApiKey {

    @Id 
    @GeneratedValue
    private Long id;

    private String keyValue;
    private boolean active = true;
    private Long ownerId;

    @ManyToOne
    private QuotaPlan plan;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getKeyValue() { return keyValue; }
    public void setKeyValue(String keyValue) { this.keyValue = keyValue; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public Long getOwnerId() { return ownerId; }
    public void setOwnerId(Long ownerId) { this.ownerId = ownerId; }

    public QuotaPlan getPlan() { return plan; }
    public void setPlan(QuotaPlan plan) { this.plan = plan; }
}
