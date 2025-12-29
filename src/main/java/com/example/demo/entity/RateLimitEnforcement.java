package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rate_limit_enforcement")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RateLimitEnforcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private ApiKey apiKey;

    @Column(nullable = false)
    private int limitPerMinute;

    @Column(nullable = false)
    private int requestsMade;
}
