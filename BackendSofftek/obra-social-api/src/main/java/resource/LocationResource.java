package resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Location;
import service.interfaces.ILocationService;
import validator.LocationValidator;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

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
	@Operation(summary = "Obtener todas las ubicaciones", description = "Retorna una lista de todas las ubicaciones.")
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Ubicaciones obtenidas con éxito"),
			@APIResponse(responseCode = "404", description = "No se encontraron ubicaciones")
	})
	public List<Location> getAllLocations() {
		return locationService.findLocations();
	}
	
	@GET
	@Path("/{id}")
	@Operation(summary = "Obtener ubicación por ID", description = "Retorna una ubicación específica por su ID.")
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Ubicación obtenida con éxito"),
			@APIResponse(responseCode = "404", description = "Ubicación no encontrada")
	})
	
	public Response getLocationById(@PathParam("id") Long id) {
		Location location = locationService.findLocationById(id);
		if (location != null) {
			return Response.ok(location).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@POST
	@Operation(summary = "Agregar una nueva ubicación", description = "Agrega una nueva ubicación.")
	@APIResponses(value = {
			@APIResponse(responseCode = "201", description = "Ubicación creada con éxito"),
			@APIResponse(responseCode = "400", description = "Solicitud incorrecta")
	})
	public Response addLocation(Location location) {
		List<String> locationErrors = locationValidator.validateLocation(location);
		if (locationErrors != null) {
			return Response.status(Response.Status.BAD_REQUEST).entity(locationErrors.toString()).build();
		}
		try {
			locationService.addLocation(location);
			return Response.status(Response.Status.CREATED).entity(location).build();
		} catch (Exception e){
			return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
		}
	}

	@PUT
	@Path("/{id}")
	@Operation(summary = "Actualizar una ubicación", description = "Actualiza una ubicación existente por su ID.")
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Ubicación actualizada con éxito"),
			@APIResponse(responseCode = "404", description = "Ubicación no encontrada")
	})
	public Response updateLocation(@PathParam("id") Long id, Location location) {
		List<String> locationErrors = locationValidator.validateLocation(location);
		if (locationErrors != null) {
			return Response.status(Response.Status.BAD_REQUEST).entity(locationErrors.toString()).build();
		}
		try {
			Location updatedLocation = locationService.editLocation(id, location);
			return Response.ok(updatedLocation).build();
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
		}
	}

	@DELETE
	@Path("/{id}")
	@Operation(summary = "Eliminar una ubicación", description = "Elimina una ubicación existente por su ID.")
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Ubicación eliminada con éxito"),
			@APIResponse(responseCode = "404", description = "Ubicación no encontrada")
	})
	public Response deleteLocation(@PathParam("id") Long id) {
		try {
			Location deletedLocation = locationService.deleteLocation(id);
			return Response.ok(deletedLocation).build();
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
		}
	}

}