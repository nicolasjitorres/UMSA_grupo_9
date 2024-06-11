package resource;


import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Specialist;
import dto.SpecialistDTO;
import dto.SpecialistMapper;
import service.SpecialistService;

@Path("/especialistas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SpecialistResource {
	@Inject
	private SpecialistService specialistService;
	@GET
	public Response getAllSpecialists(){
		List<Specialist> specialists = specialistService.getAllSpecialists();
		return Response.ok(specialists).build();
	}
	
	@GET
	@Path("{id}")
	public Response getOneSpecialist(@PathParam("id") Long id){
		return specialistService.findById(id);
	}
	
	@POST
	public Response createSpecialist(SpecialistDTO newSpecialistDTO) {
		Specialist newSpecialist = SpecialistMapper.dtoToEntity(newSpecialistDTO);
		specialistService.createSpecialist(newSpecialist);
		return Response.ok(newSpecialist).build();
	}
	
	@PUT
	@Path("{id}")
	public Response updateSpecialist(@PathParam("id") Long id, SpecialistDTO editSpecialistDTO) {
		return specialistService.edit(id, editSpecialistDTO);
	}
	
	@DELETE
	@Path("{id}")
	public Response deleteSpecialist(@PathParam("id") Long id) {
		return specialistService.delete(id);
	}

}
