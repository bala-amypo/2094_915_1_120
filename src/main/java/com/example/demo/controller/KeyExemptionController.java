package com.example.demo.controller;

import com.example.demo.entity.KeyExemption;
import com.example.demo.service.KeyExemptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/key-exemptions")
@RequiredArgsConstructor
public class KeyExemptionController {

    private final KeyExemptionService keyExemptionService;

    @GetMapping
    public List<KeyExemption> findAll() {
        return keyExemptionService.findAll();
    }
}
