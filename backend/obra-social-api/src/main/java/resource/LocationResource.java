package resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Location;
import service.interfaces.ILocationService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import java.util.List;

@Path("/ubicaciones")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LocationResource {

	@Inject
	private ILocationService locationService;

	@GET
	@Operation(summary = "Obtener todas las ubicaciones", description = "Retorna una lista de todas las ubicaciones.")
	@APIResponses(value = { @APIResponse(responseCode = "200", description = "Ubicaciones obtenidas con éxito"),
			@APIResponse(responseCode = "204", description = "No se encontraron ubicaciones") })
	public Response getAllLocations() {
		List<Location> locations = locationService.findLocations();
		if (locations.isEmpty()) {
			return Response.status(Response.Status.NO_CONTENT).build();
		} else {
			return Response.status(Response.Status.OK).entity(locations).build();
		}
	}

	@GET
	@Path("/{id}")
	@Operation(summary = "Obtener ubicación por ID", description = "Retorna una ubicación específica por su ID.")
	@APIResponses(value = { @APIResponse(responseCode = "200", description = "Ubicación obtenida con éxito"),
			@APIResponse(responseCode = "404", description = "Ubicación no encontrada") })
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
	@APIResponses(value = { @APIResponse(responseCode = "201", description = "Ubicación creada con éxito"),
			@APIResponse(responseCode = "400", description = "Solicitud incorrecta, hay datos invalidos") })
	public Response addLocation(Location location) {
		try {
			locationService.addLocation(location);
			return Response.status(Response.Status.CREATED).entity(location).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@PUT
	@Path("/{id}")
	@Operation(summary = "Actualizar una ubicación", description = "Actualiza una ubicación existente por su ID.")
	@APIResponses(value = { @APIResponse(responseCode = "200", description = "Ubicación actualizada con éxito"),
			@APIResponse(responseCode = "400", description = "Datos invalidos"),
			@APIResponse(responseCode = "404", description = "Ubicación no encontrada") })
	public Response updateLocation(@PathParam("id") Long id, Location location) {
		try {
			Location updatedLocation = locationService.editLocation(id, location);
			return Response.ok(updatedLocation).build();
		} catch (IllegalArgumentException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
		}
	}

	@DELETE
	@Path("/{id}")
	@Operation(summary = "Eliminar una ubicación", description = "Elimina una ubicación existente por su ID.")
	@APIResponses(value = { @APIResponse(responseCode = "204", description = "Ubicación eliminada con éxito"),
			@APIResponse(responseCode = "400", description = "Ubicación no encontrada"),
			@APIResponse(responseCode = "404", description = "Error al intentar borrar la ubicación") })
	public Response deleteLocation(@PathParam("id") Long id) {
		try {
			Location deletedLocation = locationService.deleteLocation(id);
			return Response.status(204).build();
		} catch (IllegalArgumentException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
		}
	}

}