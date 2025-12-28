@Service
public class ApiKeyServiceImpl implements ApiKeyService {
    private final ApiKeyRepository apiKeyRepository;
    private final QuotaPlanRepository quotaPlanRepository;

    // Strict requirement: Constructor Injection
    public ApiKeyServiceImpl(ApiKeyRepository apiKeyRepository, QuotaPlanRepository quotaPlanRepository) {
        this.apiKeyRepository = apiKeyRepository;
        this.quotaPlanRepository = quotaPlanRepository;
    }

    @Override
    public ApiKey createApiKey(ApiKey apiKey) {
        // Business Rule: Plan must be active
        QuotaPlan plan = quotaPlanRepository.findById(apiKey.getPlan().getId())
                .orElseThrow(() -> new ResourceNotFoundException("QuotaPlan not found"));
        
        if (!plan.getActive()) {
            throw new BadRequestException("Cannot associate an inactive plan");
        }
        
        // Business Rule: KeyValue must be unique
        if (apiKeyRepository.findByKeyValue(apiKey.getKeyValue()).isPresent()) {
            throw new IllegalArgumentException("API Key value already exists");
        }
        
        return apiKeyRepository.save(apiKey);
    }
    // Other methods: updateApiKey, deactivateApiKey, etc.
}