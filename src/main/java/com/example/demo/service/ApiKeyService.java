public interface ApiKeyService {

    ApiKey createApiKey(ApiKey apiKey);

    ApiKey getApiKeyById(Long id);

    ApiKey getApiKeyByValue(String keyValue);

    List<ApiKey> getAllApiKeys();

    void deactivateApiKey(Long id);
}
