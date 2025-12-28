@Entity
@Table(name = "quota_plans")
public class QuotaPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String planName;

    @Column(nullable = false)
    private Integer dailyLimit;

    private String description;
    private Boolean active = true;

    // Must include no-arg and parameterized constructors
    public QuotaPlan() {}
    public QuotaPlan(String planName, Integer dailyLimit, String description, Boolean active) {
        this.planName = planName;
        this.dailyLimit = dailyLimit;
        this.description = description;
        this.active = active;
    }
    // Getters and Setters...
}