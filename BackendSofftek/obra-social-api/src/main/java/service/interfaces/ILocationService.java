package service.interfaces;

import model.Location;

import java.util.List;

public interface ILocationService {

    public List<Location> getLocations();
    public Location getLocationById(Long id);
    public void addLocation(Location location) throws Exception;
    public void deleteLocation(Long id);
    public void editLocation(Long id, Location location);

}
