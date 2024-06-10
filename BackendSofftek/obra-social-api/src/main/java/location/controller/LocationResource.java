package location.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import location.model.Location;
import location.service.LocationService;

import java.util.List;

@Path("/locations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class LocationResource {

    @Inject
    private LocationService locationService;

    @GET
    public List<Location> getAllLocations() {
        return locationService.getLocations();
    }

    @GET
    @Path("/{id}")
    public Response getLocationById(@PathParam("id") Long id) {
        Location location = locationService.getLocationById(id);
        if (location != null) {
            return Response.ok(location).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    public Response addLocation(Location location) {
        locationService.addLocation(location);
        return Response.status(Response.Status.CREATED).entity(location).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateLocation(@PathParam("id") Long id, Location location) {
        Location existingLocation = locationService.getLocationById(id);
        if (existingLocation != null) {
            locationService.editLocation(id, location);
            location.setId(id);
            return Response.ok(location).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteLocation(@PathParam("id") Long id) {
        Location location = locationService.getLocationById(id);
        if (location != null) {
            locationService.deleteLocation(id);
            return Response.ok().entity("Location deleted successfully").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Location not found").build();
        }
    }

}
