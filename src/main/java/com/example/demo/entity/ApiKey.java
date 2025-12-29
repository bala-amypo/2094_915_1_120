package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "api_keys")
public class ApiKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String keyValue;

    @Column(nullable = false)
    private boolean active = true;

    public ApiKey() {}

    public ApiKey(String keyValue) {
        this.keyValue = keyValue;
    }

    public Long getId() {
        return id;
    }

    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
