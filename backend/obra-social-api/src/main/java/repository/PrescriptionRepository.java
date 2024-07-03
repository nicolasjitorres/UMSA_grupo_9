package repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import model.Prescription;

@ApplicationScoped
public class PrescriptionRepository implements PanacheRepository<Prescription> {
    public boolean prescriptionExistsByShiftId(Long shiftId) {
        return find("shift.id", shiftId).firstResultOptional().isPresent();
    }
}
