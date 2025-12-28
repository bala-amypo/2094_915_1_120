public interface ApiUsageLogService {

    ApiUsageLog logUsage(ApiUsageLog log);

    List<ApiUsageLog> getUsageForToday(Long apiKeyId);

    List<ApiUsageLog> getUsageForApiKey(Long apiKeyId);

    int countRequestsToday(Long apiKeyId);
}
