package repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import model.Location;

@ApplicationScoped
public class LocationRepository implements PanacheRepository<Location> {

    public Location findByDetails(String street, String locality, String province, String country) {
        return find("street = ?1 and locality = ?2 and province = ?3 and country = ?4",
                street, locality, province, country)
                .firstResult();
    }

}
