package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "quota_plans")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuotaPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private int monthlyQuota;

    @Column(nullable = false)
    private int perMinuteLimit;
}
