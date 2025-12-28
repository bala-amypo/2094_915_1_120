package com.example.demo.service;
import com.example.demo.model.*;
import java.util.List;

public interface ApiUsageLogService {
    ApiUsageLog logUsage(ApiUsageLog log);
    List<ApiUsageLog> getUsageForToday(Long keyId);
    List<ApiUsageLog> getUsageForApiKey(Long keyId);
    int countRequestsToday(Long keyId);
}
