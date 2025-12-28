package com.example.demo.model;

import java.sql.Timestamp;

public class ApiKey {

    private Long id;
    private String key;
    private boolean active;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private QuotaPlan quotaPlan;

    public ApiKey() {}

    public ApiKey(String key, Long id, QuotaPlan quotaPlan) {
        this.key = key;
        this.id = id;
        this.quotaPlan = quotaPlan;
        this.active = true;
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.updatedAt = this.createdAt;
    }

    public Long getId() { return id; }
    public String getKey() { return key; }
    public boolean getActive() { return active; }
    public Timestamp getCreatedAt() { return createdAt; }
    public Timestamp getUpdatedAt() { return updatedAt; }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
