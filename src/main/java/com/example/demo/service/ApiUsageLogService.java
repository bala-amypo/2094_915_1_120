package com.example.demo.service;

import com.example.demo.entity.ApiUsageLog;
import java.util.List;

public interface ApiUsageLogService {

    ApiUsageLog logUsage(ApiUsageLog log);

    List<ApiUsageLog> getUsageForApiKey(Long apiKeyId);

    List<ApiUsageLog> getUsageForToday(Long apiKeyId);

    int countRequestsToday(Long apiKeyId);
}
