package repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import model.Prescription;

@ApplicationScoped
public class PrescriptionRepository implements PanacheRepository<Prescription> {
}
