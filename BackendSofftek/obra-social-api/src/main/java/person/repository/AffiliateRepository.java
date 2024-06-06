package person.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import person.model.Affiliate;

@ApplicationScoped
public class AffiliateRepository implements PanacheRepository<Affiliate> {
}
