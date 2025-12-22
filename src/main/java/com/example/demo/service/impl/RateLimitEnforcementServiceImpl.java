@Override
public RateLimitEnforcementDto createEnforcement(RateLimitEnforcementDto dto) {
    // Business Rule: limitExceededBy must be >= 1 
    if (dto.getLimitExceededBy() == null || dto.getLimitExceededBy() < 1) {
        throw new BadRequestException("Limit exceeded must be at least 1");
    }

    ApiKey apiKey = apiKeyRepository.findById(dto.getApiKeyId())
            .orElseThrow(() -> new ResourceNotFoundException("ApiKey not found"));

    RateLimitEnforcement entity = new RateLimitEnforcement();
    entity.setApiKey(apiKey); // Now found
    entity.setLimitExceededBy(dto.getLimitExceededBy()); // Now found
    entity.setBlockedAt(dto.getBlockedAt()); // Ensure dto.getBlockedAt() returns LocalDateTime
    entity.setMessage(dto.getMessage()); // Now found

    RateLimitEnforcement saved = enforcementRepo.save(entity);
    return mapToDto(saved);
}