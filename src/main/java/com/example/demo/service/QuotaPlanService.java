public interface QuotaPlanService {

    QuotaPlan createPlan(QuotaPlan plan);

    QuotaPlan updatePlan(Long id, QuotaPlan plan);

    QuotaPlan getQuotaPlanById(Long id);

    List<QuotaPlan> getAllPlans();
}
