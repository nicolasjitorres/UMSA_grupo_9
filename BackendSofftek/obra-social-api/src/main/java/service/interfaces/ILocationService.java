package service.interfaces;

import model.Location;

import java.util.List;

public interface ILocationService {

    public List<Location> findLocations();
    public Location findLocationById(Long id);
    public Location addLocation(Location location) throws Exception;
    public Location deleteLocation(Long id);
    public Location editLocation(Long id, Location location);

}
