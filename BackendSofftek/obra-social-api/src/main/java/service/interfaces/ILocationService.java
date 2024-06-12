package service.interfaces;

import model.Location;

import java.util.List;


public interface ILocationService {

    public List<Location> getAllLocations();
    public Location getLocationById(Long id);
    public Location addLocation(Location location);
    public Location editLocation(Long id, Location location);
    public Location deleteLocation(Long id);

}
