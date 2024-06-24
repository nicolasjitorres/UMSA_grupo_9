package resource;

import dto.mappers.MapperEntityToDTO;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Schedule;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import service.interfaces.IScheduleService;

import java.util.List;

@Path("/horarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ScheduleResource {
	@Inject
	private IScheduleService scheduleService;

	@GET
	@Operation(summary = "Obtener todos los horarios", description = "Retorna una lista de todos los horarios.")
	@APIResponses(value = { @APIResponse(responseCode = "200", description = "Horarios obtenidos con éxito"),
			@APIResponse(responseCode = "204", description = "No hay horarios cargados cargados en el sistema") })
	public Response getSchedules() {
		List<Schedule> schedules = scheduleService.findSchedules();
		if (schedules.isEmpty()) {
			return Response.status(204).build();
		} else {
			return Response.ok(schedules).build();
		}
	}

	@GET
	@Path("/{id}")
	@Operation(summary = "Obtener un horario por ID", description = "Retorna un horario basado en el ID proporcionado.")
	@APIResponses(value = { @APIResponse(responseCode = "200", description = "Horario obtenido con éxito"),
			@APIResponse(responseCode = "404", description = "Horario no encontrado") })
	public Response getScheduleById(@PathParam("id") Long id) {
		Schedule schedule = scheduleService.findScheduleById(id);
		if (schedule == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("No existe un horario con el id " + id).build();
		} else {
			return Response.ok(MapperEntityToDTO.entityToDTO(schedule)).build();
		}
	}
	@GET
	@Path("/especialista/{idSpecialist}")
	@Operation(summary = "Obtener un horario de determinado especialista ID", description = "Retorna un horario basado en el ID del especialista proporcionado.")
	@APIResponses(value = { @APIResponse(responseCode = "200", description = "Horario obtenido con éxito"),
			@APIResponse(responseCode = "204", description = "Especialista sin horario no encontrado") })
	public Response getScheduleByIdSpecialist(@PathParam("idSpecialist") Long idSpecialist) {
		List<Schedule> schedules = scheduleService.findScheduleByIDSpecialist(idSpecialist);
		if (schedules.isEmpty()) {
			return Response.status(204).build();
		} else {
			return Response.ok(schedules).build();
		}
	}

	@POST
	@Path("/{idSpecialist}")
	@Operation(summary = "Crear un nuevo horario", description = "Agrega un nuevo horario al sistema.")
	@APIResponses(value = { @APIResponse(responseCode = "201", description = "Horario creado con éxito"),
			@APIResponse(responseCode = "400", description = "Solicitud incorrecta, hay datos inválidos") })
	public Response addSchedule(@PathParam("idSpecialist") Long id,@Valid Schedule schedule) {
		try {
			scheduleService.addSchedule(id, schedule);
			return Response.status(Response.Status.CREATED).entity(schedule).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@DELETE
	@Path("/{id}")
	@Operation(summary = "Eliminar un horario", description = "Elimina un horario basado en el ID proporcionado.")
	@APIResponses(value = { @APIResponse(responseCode = "200", description = "Horario eliminado con éxito"),
			@APIResponse(responseCode = "404", description = "Horario no encontrado") })
	public Response deleteScheduleById(@PathParam("id") Long id) {
		try {
			Schedule deletedSchedule = scheduleService.deleteSchedule(id);
			return Response.ok(deletedSchedule).build();
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
		}

	}

	@PUT
	@Path("/{id}")
	@Operation(summary = "Actualizar un horario", description = "Actualiza un horario basado en el ID proporcionado.")
	@APIResponses(value = { @APIResponse(responseCode = "200", description = "Horario actualizado con éxito"),
			@APIResponse(responseCode = "400", description = "Solicitud incorrecta, hay datos inválidos"),
			@APIResponse(responseCode = "404", description = "Horario no encontrado") })
	public Response updateSchedule(@PathParam("id") Long id, Schedule schedule) {
		try {
			Schedule updatedSchedule = scheduleService.editSchedule(id, schedule);
			return Response.ok(updatedSchedule).build();
		} catch (IllegalArgumentException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
		}
	}

}