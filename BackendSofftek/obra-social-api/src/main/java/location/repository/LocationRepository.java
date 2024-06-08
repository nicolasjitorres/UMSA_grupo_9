package location.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import location.model.Location;

import java.util.Optional;

@ApplicationScoped
public class LocationRepository implements PanacheRepository<Location> {

    public Optional<Location> findByDetails(String street, String locality, String province, String country) {
        return find("street = ?1 and locality = ?2 and province = ?3 and country = ?4",
                street, locality, province, country)
                .firstResultOptional();
    }

}
