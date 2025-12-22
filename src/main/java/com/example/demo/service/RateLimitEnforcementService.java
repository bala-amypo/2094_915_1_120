@Override
public RateLimitEnforcementDto createEnforcement(RateLimitEnforcementDto enforcementDto) {
    // 1. Validate: Limit exceeded must be >= 1 [cite: 808, 1467]
    if (enforcementDto.getLimitExceededBy() == null || enforcementDto.getLimitExceededBy() < 1) {
        throw new BadRequestException("Limit exceeded must be at least 1");
    }

    // 2. Fetch API Key and validate existence [cite: 1469, 1502]
    ApiKey apiKey = apiKeyRepository.findById(enforcementDto.getApiKeyId())
            .orElseThrow(() -> new ResourceNotFoundException("ApiKey not found"));

    // 3. Map DTO to Entity [cite: 806, 1471]
    RateLimitEnforcement enforcement = new RateLimitEnforcement();
    enforcement.setApiKey(apiKey);
    enforcement.setBlockedAt(enforcementDto.getBlockedAt()); // Ensure LocalDateTime usage
    enforcement.setLimitExceededBy(enforcementDto.getLimitExceededBy());
    enforcement.setMessage(enforcementDto.getMessage());

    // 4. Save and return mapped DTO [cite: 1518]
    RateLimitEnforcement saved = rateLimitEnforcementRepository.save(enforcement);
    return mapToDto(saved);
}