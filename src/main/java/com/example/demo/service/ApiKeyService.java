public interface ApiKeyService {

    ApiKey createApiKey(ApiKey apiKey);

    ApiKey updateApiKey(Long id, ApiKey apiKey);

    ApiKey getApiKeyById(Long id);

    ApiKey getApiKeyByValue(String key);

    List<ApiKey> getAllApiKeys();

    void revokeApiKey(Long id);
}
