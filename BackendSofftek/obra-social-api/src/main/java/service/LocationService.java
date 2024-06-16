package service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import model.Location;
import model.Specialist;
import repository.LocationRepository;
import service.interfaces.ILocationService;
import validator.LocationValidator;

import java.util.List;

@ApplicationScoped
@Transactional
public class LocationService implements ILocationService {

    @Inject
    private LocationRepository locationRepository;
    @Inject
    private LocationValidator locationValidator;

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
        List<String> existingErrors = locationValidator.validateLocation(location);
        if (existingErrors != null) throw new IllegalArgumentException(existingErrors.toString());
        locationRepository.persist(location);
        return location;
    }

    public Location deleteLocation(Long id) throws Exception {
        Location deletedLocation = locationRepository.findById(id);
        if (deletedLocation == null) throw new Exception("No existe esa ubicación con id: " + id);
        if (!deletedLocation.getSpecialists().isEmpty())
            throw new IllegalArgumentException("No se puede borrar la ubicación porque está asociada a algún especialista.");
        locationRepository.deleteById(id);
        return deletedLocation;

    }

    @Override
    public Location editLocation(Long id, Location location) throws Exception {
        Location existingLocation = locationRepository.findById(id);
        if (existingLocation == null) throw new Exception("No existe esa ubicación con id: " + id);
        List<String> existingErrors = locationValidator.validateLocation(location);
        if (existingErrors != null) throw new IllegalArgumentException(existingErrors.toString());

        existingLocation.setCountry(location.getCountry());
        existingLocation.setProvince(location.getProvince());
        existingLocation.setLocality(location.getLocality());
        existingLocation.setStreet(location.getStreet());

        locationRepository.persistAndFlush(existingLocation);
        return existingLocation;

    }


}