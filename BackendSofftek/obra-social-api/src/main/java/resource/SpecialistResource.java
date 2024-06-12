package resource;

import java.util.List;
import java.util.stream.Collectors;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Specialist;
import dto.SpecialistDTO;
import dto.mappers.SpecialistMapper;
import service.interfaces.ISpecialistService;

@Path("/especialistas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SpecialistResource {
	@Inject
	private ISpecialistService specialistService;
	
	@GET
	public Response getAllSpecialists() {
		List<Specialist> specialists = specialistService.getAllSpecialists();
		List<SpecialistDTO> specialistDTOs = specialists.stream().map(SpecialistMapper::entityToDto)
				.collect(Collectors.toList());
		return Response.ok(specialistDTOs).build();
	}

	@GET
	@Path("{id}")
	public Response getOneSpecialist(@PathParam("id") Long id) {
		Specialist existSpecialist = specialistService.getSpecialistById(id);
		if (existSpecialist == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("No existe el usuario con el id " + id + ".").build();
		} else {
			return Response.ok(SpecialistMapper.entityToDto(existSpecialist)).build();
		}
	}

	@POST
	public Response createSpecialist(SpecialistDTO newSpecialistDTO) {
		Specialist newSpecialist = SpecialistMapper.createSpecialistDto(newSpecialistDTO);
		Specialist specialist = specialistService.addSpecialist(newSpecialist);
		return Response.ok(SpecialistMapper.entityToDto(specialist)).build();
	}

	@PUT
	@Path("{id}")
	public Response updateSpecialist(@PathParam("id") Long id, SpecialistDTO editSpecialistDTO) {
		Specialist editSpecialist = SpecialistMapper.createSpecialistDto(editSpecialistDTO);
		Specialist editedSpecialist = specialistService.editSpecialist(id, editSpecialist);
		return Response.ok(SpecialistMapper.entityToDto(editedSpecialist)).build();
	}

	@DELETE
	@Path("{id}")
	public Response deleteSpecialist(@PathParam("id") Long id) {
		Specialist deletedSpecialist = specialistService.deleteSpecialist(id);
		return Response.ok(SpecialistMapper.entityToDto(deletedSpecialist)).build();
	}

}
