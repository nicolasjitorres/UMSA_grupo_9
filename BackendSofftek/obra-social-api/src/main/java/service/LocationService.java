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
	public List<Location> getAllLocations() {
		return locationRepository.listAll();
	}

	@Override
	public Location getLocationById(Long id) {
		return locationRepository.findById(id);
	}

	@Override
	public Location addLocation(Location location) {
		Location existingLocation = locationRepository.findByDetails(location.getStreet(), location.getLocality(), location.getProvince(), location.getCountry());
		if (existingLocation == null) {
			locationRepository.persist(location);
			return location;			
		} else {
			return null;
		}
	}

	@Override
	public Location editLocation(Long id, Location location) {
		Location existingLocation = locationRepository.findById(id);
		if (existingLocation != null) {
			Location sameLocation = locationRepository.findByDetails(location.getStreet(), location.getLocality(), location.getProvince(), location.getCountry());
			if (sameLocation == null || sameLocation.getId().equals(existingLocation.getId())) {
				existingLocation.setStreet(location.getStreet());
				existingLocation.setLocality(location.getLocality());
				existingLocation.setProvince(location.getProvince());
				existingLocation.setCountry(location.getCountry());
				locationRepository.persistAndFlush(existingLocation);
				return existingLocation;
			} else {
				return null;
			}
		} else {
			return null;
//			throw new Exception("No existe esa ubicación con id: " + id);
		}
	}

	@Override
	public Location deleteLocation(Long id) {
		Location existingLocation = locationRepository.findById(id);
		if (existingLocation != null) {
			locationRepository.deleteById(id);
			return existingLocation;
		} else {
			return null;
//			throw new Exception("No existe esa ubicación con id: " + id);
		}
	}
}
