package repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import model.Affiliate;

@ApplicationScoped
public class AffiliateRepository implements PanacheRepository<Affiliate> {
    public Affiliate findByDni(String dni) {
        return find("dni", dni).firstResult();
    }
    public Affiliate findByHealthInsuranceCode(String healthInsuranceCode) {
        return find("healthInsuranceCode", healthInsuranceCode).firstResult();
    }

}
