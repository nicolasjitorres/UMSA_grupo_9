package location.service;

import location.model.Location;

import java.util.List;

public interface ILocationService {

    public List<Location> getLocations();
    public Location getLocation(Long id);
    public void addLocation(Location location);
    public void deleteLocation(Long id);
    public void editLocation(Long id, Location location);

}
