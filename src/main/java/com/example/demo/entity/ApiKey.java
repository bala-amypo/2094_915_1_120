package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "api_keys")
public class ApiKey{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String apiKey;

    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;

    @ManyToOne
    private QuotaPlan quotaPlan;
}
