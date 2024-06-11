package location.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import location.model.Location;
import location.repository.LocationRepository;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class LocationService implements ILocationService{

    @Inject
    private LocationRepository locationRepository;
    @Override
    public List<Location> getLocations() {
       return locationRepository.findAll().stream().toList();
    }

    @Override
    public Location getLocationById(Long id) {
        return locationRepository.findById(id);
    }

    @Override
    public void addLocation(Location location) throws Exception {
        Location existingLocation = locationRepository.findByDetails(
                location.getStreet(),
                location.getLocality(),
                location.getProvince(),
                location.getCountry()
        );
        if (existingLocation!=null) throw new Exception("ya existe esta locacion");
        locationRepository.persist(location);
    }

    @Override
    public void deleteLocation(Long id) {
    locationRepository.deleteById(id);
    }

    @Override
    public void editLocation(Long id, Location location) {
        Location existingLocation = locationRepository.findById(id);
        if (existingLocation != null) {
            if (location.getStreet() != null) {
                existingLocation.setStreet(location.getStreet());
            }
            if (location.getLocality() != null) {
                existingLocation.setLocality(location.getLocality());
            }
            if (location.getProvince() != null) {
                existingLocation.setProvince(location.getProvince());
            }
            if (location.getCountry() != null) {
                existingLocation.setCountry(location.getCountry());
            }
            locationRepository.persist(existingLocation);
        }
    }


}
