package location.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import location.model.Location;

@ApplicationScoped
public class LocationRepository implements PanacheRepository<Location> {

}
