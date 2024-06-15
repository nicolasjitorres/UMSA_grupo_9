package resource;

import java.util.List;
import java.util.stream.Collectors;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import model.Affiliate;
import model.Specialist;
import dto.SpecialistDTO;
import dto.mappers.AffiliateMapper;
import dto.mappers.SpecialistMapper;
import service.interfaces.ISpecialistService;
import validator.SpecialistValidator;

@Path("/especialistas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SpecialistResource {
	
	private ISpecialistService specialistService;
	private SpecialistValidator specialistValidator;
	
	@Inject
	public SpecialistResource(ISpecialistService specialistService, SpecialistValidator specialistValidator) {
		super();
		this.specialistService = specialistService;
		this.specialistValidator = specialistValidator;
	}

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
		List<String> specialistErrors = specialistValidator.validateSpecialist(newSpecialistDTO);
		if (specialistErrors != null) {
			return Response.status(Response.Status.BAD_REQUEST).entity(specialistErrors.toString()).build();
		}
		try {
			Specialist specialist = specialistService.addSpecialist(SpecialistMapper.createSpecialistDto(newSpecialistDTO));
			return Response.ok(SpecialistMapper.entityToDto(specialist)).build();
		} catch (Exception e) {
			return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
		}		
	}

	@PUT
	@Path("{id}")
	public Response updateSpecialist(@PathParam("id") Long id, SpecialistDTO editSpecialistDTO) {
		List<String> specialistErrors = specialistValidator.validateSpecialist(editSpecialistDTO);
		if (specialistErrors != null) {
			return Response.status(Response.Status.BAD_REQUEST).entity(specialistErrors.toString()).build();
		}
		try {
			Specialist editedSpecialist = specialistService.editSpecialist(id, SpecialistMapper.createSpecialistDto(editSpecialistDTO));
			return Response.ok(SpecialistMapper.entityToDto(editedSpecialist)).build();
		} catch (Exception e) {
			return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
		}
	}

	@DELETE
	@Path("{id}")
	public Response deleteSpecialist(@PathParam("id") Long id) {
		Specialist deletedSpecialist = specialistService.deleteSpecialist(id);
		return Response.ok(SpecialistMapper.entityToDto(deletedSpecialist)).build();
	}

}
