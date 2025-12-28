package com.example.demo.model;

public class QuotaPlan {

    private Long id;
    private String name;
    private Integer dailyLimit;
    private String description;
    private boolean active;

    public QuotaPlan() {}

    public QuotaPlan(String name, Integer dailyLimit, String description) {
        this.name = name;
        this.dailyLimit = dailyLimit;
        this.description = description;
        this.active = true;
    }

    public String getDescription() { return description; }
    public boolean getActive() { return active; }
    public void setDescription(String description) { this.description = description; }
}
