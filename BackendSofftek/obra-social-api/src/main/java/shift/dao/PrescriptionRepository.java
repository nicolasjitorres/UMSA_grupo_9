package shift.dao;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import shift.entity.Prescription;

@ApplicationScoped
public class PrescriptionRepository implements PanacheRepository<Prescription> {
}
