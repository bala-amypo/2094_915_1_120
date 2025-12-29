package com.example.demo.service;

import com.example.demo.entity.ApiUsageLog;

import java.util.List;

public interface ApiUsageLogService {

    List<ApiUsageLog> findAll();
}
