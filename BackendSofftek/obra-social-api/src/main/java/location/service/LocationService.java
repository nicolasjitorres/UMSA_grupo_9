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
    public void addLocation(Location location)
    {
        Optional<Location> existingLocation = locationRepository.findByDetails(
                location.getStreet(),
                location.getLocality(),
                location.getProvince(),
                location.getCountry()
        );
        if (existingLocation.isPresent()) {
            //hacer lo que quieras hacer si es que existe
        } else {
            //sino lo persiste
            locationRepository.persist(location);
        }
    }

    @Override
    public void deleteLocation(Long id) {
    locationRepository.deleteById(id);
    }

    @Override
    public void editLocation(Long id, Location location) {
        Location existingLocation = locationRepository.findById(id);
        if (existingLocation != null) {
            existingLocation.setStreet(location.getStreet());
            existingLocation.setLocality(location.getLocality());
            existingLocation.setProvince(location.getProvince());
            existingLocation.setCountry(location.getCountry());
            locationRepository.persist(existingLocation);
        }
    }

}
