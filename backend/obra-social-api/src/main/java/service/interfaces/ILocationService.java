package service.interfaces;

import jakarta.enterprise.context.ApplicationScoped;
import model.Location;

import java.util.List;

@ApplicationScoped
public interface ILocationService {

    public List<Location> findLocations();
    public Location findLocationById(Long id);
    public Location addLocation(Location location) throws Exception;
    public Location deleteLocation(Long id) throws Exception;
    public Location editLocation(Long id, Location location) throws Exception ;
    public Location findLocationByDetails(Location location) throws Exception;
}