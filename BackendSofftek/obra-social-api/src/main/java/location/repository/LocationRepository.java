package location.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import location.model.Location;

@ApplicationScoped
public class LocationRepository implements PanacheRepository<Location> {

}
