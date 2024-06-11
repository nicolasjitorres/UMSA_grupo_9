package service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import model.Location;
import repository.LocationRepository;
import service.interfaces.ILocationService;

import java.util.List;

@ApplicationScoped
@Transactional
public class LocationService implements ILocationService {

    @Inject
    private LocationRepository locationRepository;
    @Override
    public List<Location> findLocations() {
       return locationRepository.findAll().stream().toList();
    }

    @Override
    public Location findLocationById(Long id) {
        return locationRepository.findById(id);
    }

    @Override
    public Location addLocation(Location location) throws Exception {
        Location existingLocation = locationRepository.findByDetails(
                location.getStreet(),
                location.getLocality(),
                location.getProvince(),
                location.getCountry()
        );
        if (existingLocation!=null) throw new Exception("ya existe esta locacion");
        locationRepository.persist(location);

		return null;
	}

	@Override
	public Location deleteLocation(Long id) {
		locationRepository.deleteById(id);

		return null;
	}

	@Override
	public Location editLocation(Long id, Location location) {
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

		return null;
    }


}