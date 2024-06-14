package repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import model.Affiliate;

@ApplicationScoped
public class AffiliateRepository implements PanacheRepository<Affiliate> {
}
