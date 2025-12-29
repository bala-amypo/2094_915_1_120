package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "key_exemptions")
public class KeyExemption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "api_key_id", nullable = false, unique = true)
    private ApiKey apiKey;

    private boolean exempted = true;

    public Long getId() {
        return id;
    }

    public ApiKey getApiKey() {
        return apiKey;
    }

    public void setApiKey(ApiKey apiKey) {
        this.apiKey = apiKey;
    }

    public boolean isExempted() {
        return exempted;
    }
}
