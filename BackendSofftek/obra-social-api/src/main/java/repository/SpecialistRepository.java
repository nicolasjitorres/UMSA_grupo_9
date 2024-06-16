package repository;

import java.util.Optional;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import model.Affiliate;
import model.Specialist;

@ApplicationScoped
public class SpecialistRepository implements PanacheRepository<Specialist>{

	public Optional<Specialist> findByLocation(String street, String locality, String province, String country) {
        return find("location.street = ?1 and location.locality = ?2 and location.province = ?3 and location.country = ?4",
                    street, locality, province, country).firstResultOptional();
    }
    public Specialist findByDni(String dni) {
        return find("dni", dni).firstResult();
    }
	
}
