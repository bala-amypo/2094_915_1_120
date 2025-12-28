public interface RateLimitEnforcementService {

    RateLimitEnforcement enforce(RateLimitEnforcement enforcement);

    List<RateLimitEnforcement> getAll();
}
