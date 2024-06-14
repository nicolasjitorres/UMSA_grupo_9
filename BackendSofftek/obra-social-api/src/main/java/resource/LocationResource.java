package resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Location;
import service.interfaces.ILocationService;
import validator.LocationValidator;

import java.util.List;

@Path("/ubicaciones")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class LocationResource {
	
	private ILocationService locationService;
	private LocationValidator locationValidator;
	
	@Inject
	public LocationResource(ILocationService locationService, LocationValidator locationValidator) {
		super();
		this.locationService = locationService;
		this.locationValidator = locationValidator;
	}

	@GET
	public List<Location> getAllLocations() {
		return locationService.findLocations();
	}

	@GET
	@Path("/{id}")
	public Response getLocationById(@PathParam("id") Long id) {
		Location location = locationService.findLocationById(id);
		if (location != null) {
			return Response.ok(location).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@POST
	public Response addLocation(Location location) {
//		List<String> locationErrors = locationValidator.validateLocation(location);
//		if (locationErrors != null) {
//			return Response.status(Response.Status.BAD_REQUEST).entity(locationErrors.toString()).build();
//		}
//		locationService.addLocation(location);
//		return Response.status(Response.Status.CREATED).entity(location).build();
		try {
			locationService.addLocation(location);
			return Response.status(Response.Status.CREATED).entity(location).build();
		} catch (Exception e){
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@PUT
	@Path("/{id}")
	public Response updateLocation(@PathParam("id") Long id, Location location) {
		try {
			Location updatedLocation = locationService.editLocation(id, location);
			return Response.ok(updatedLocation).build();
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
		}
	}

	@DELETE
	@Path("/{id}")
	public Response deleteLocation(@PathParam("id") Long id) {
		try {
			Location deletedLocation = locationService.deleteLocation(id);
			return Response.ok(deletedLocation).build();
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
		}
	}

}