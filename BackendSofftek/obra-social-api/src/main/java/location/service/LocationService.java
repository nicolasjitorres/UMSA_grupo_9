package location.service;

import jakarta.enterprise.context.ApplicationScoped;
import location.model.Location;

import java.util.List;

@ApplicationScoped
public class LocationService implements ILocationService{

    @Override
    public List<Location> getLocations() {
        return null;
    }

    @Override
    public Location getLocation(Long id) {
        return null;
    }

    @Override
    public void addLocation(Location location) {

    }

    @Override
    public void deleteLocation(Long id) {

    }

    @Override
    public void editLocation(Long id, Location location) {

    }
}
