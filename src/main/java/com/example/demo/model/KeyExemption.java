public class KeyExemption {

    private Long id;
    private ApiKey apiKey;
    private String notes;
    private boolean unlimitedAccess;
    private Integer temporaryExtensionLimit;
    private Instant validUntil;

    public KeyExemption() {}

    public ApiKey getApiKey() { return apiKey; }
    public void setApiKey(ApiKey apiKey) { this.apiKey = apiKey; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public boolean getUnlimitedAccess() { return unlimitedAccess; }
    public void setUnlimitedAccess(boolean unlimitedAccess) {
        this.unlimitedAccess = unlimitedAccess;
    }

    public Integer getTemporaryExtensionLimit() { return temporaryExtensionLimit; }
    public void setTemporaryExtensionLimit(Integer temporaryExtensionLimit) {
        this.temporaryExtensionLimit = temporaryExtensionLimit;
    }

    public Instant getValidUntil() { return validUntil; }
    public void setValidUntil(Instant validUntil) { this.validUntil = validUntil; }
}
