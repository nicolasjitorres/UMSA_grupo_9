package resource;

import java.util.List;
import java.util.stream.Collectors;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import model.Specialist;
import dto.SpecialistDTO;
import dto.mappers.SpecialistMapper;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import service.interfaces.ISpecialistService;

@Path("/especialistas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SpecialistResource {

	@Inject
	private ISpecialistService specialistService;


	@GET
	@Operation(summary = "Obtener todos los especialistas", description = "Retorna una lista de todos los especialistas de la obra social.")
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Especialistas obtenidos con éxito"),
			@APIResponse(responseCode = "204", description = "No hay especialistas cargados en el sistema")
	})
	public Response getAllSpecialists() {
		List<Specialist> specialists = specialistService.getAllSpecialists();
		if(!specialists.isEmpty()) {
		List<SpecialistDTO> specialistDTOs = specialists.stream().map(SpecialistMapper::entityToDto)
				.collect(Collectors.toList());
				return Response.ok(specialistDTOs).build();
		} else {
			return Response.status(204).build();
		}
	}

	@GET
	@Path("{id}")
	@Operation(summary = "Obtener un especialista por ID", description = "Retorna un especialista basado en el ID proporcionado.")
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Especialista obtenido con éxito"),
			@APIResponse(responseCode = "404", description = "Especialista no encontrado")
	})
	public Response getOneSpecialist(@PathParam("id") Long id) {
		Specialist existSpecialist = specialistService.getSpecialistById(id);
		if (existSpecialist == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("No existe el usuario con el id " + id + ".").build();
		} else {
			return Response.ok(SpecialistMapper.entityToDto(existSpecialist)).build();
		}
	}

	@POST
	@Operation(summary = "Crear un especialista", description = "Crea un nuevo especialista.")
	@APIResponse(responseCode = "200", description = "Especialista creado con éxito")
	@APIResponse(responseCode = "400", description = "Solicitud incorrecta, hay datos invalidos")
	//404 NO VA A TIRAR NUNCA
	public Response createSpecialist(SpecialistDTO newSpecialistDTO) {
		try {
			Specialist specialist = specialistService.addSpecialist(SpecialistMapper.createSpecialistDto(newSpecialistDTO));
			return Response.ok(SpecialistMapper.entityToDto(specialist)).build();
		} catch (IllegalArgumentException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		} catch (Exception e) {
			return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
		}		
	}

	@PUT
	@Path("{id}")
	@Operation(summary = "Actualizar un Especialista", description = "Actualiza un especialista existente.")
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Especialista actualizado con éxito"),
			@APIResponse(responseCode = "400", description = "Solicitud incorrecta, hay datos inválidos"),
			@APIResponse(responseCode = "404", description = "Especialista no encontrado")
	})
	public Response updateSpecialist(@PathParam("id") Long id, SpecialistDTO editSpecialistDTO) {
		try {
			Specialist editedSpecialist = specialistService.editSpecialist(id, SpecialistMapper.createSpecialistDto(editSpecialistDTO));
			return Response.ok(SpecialistMapper.entityToDto(editedSpecialist)).build();
		} catch (IllegalArgumentException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		} catch (Exception e) {
			return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
		}
	}

	@DELETE
	@Path("{id}")
	@Operation(summary = "Eliminar un especialista", description = "Elimina un especialista existente.")
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Especialista eliminado con éxito"),
			@APIResponse(responseCode = "404", description = "Especialista no encontrado")
	})
	public Response deleteSpecialist(@PathParam("id") Long id) {
		try{
			Specialist deletedSpecialist = specialistService.deleteSpecialist(id);
			return Response.ok(SpecialistMapper.entityToDto(deletedSpecialist)).build();
		}catch (Exception e)
		{
			return Response.status(Status.NOT_FOUND).entity((e.getMessage())).build();
		}
	}

}
