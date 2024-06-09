package person.controller;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import person.model.Specialist;
import person.service.SpecialistService;

@Path("/especialistas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SpecialistResource {
	@Inject
	private SpecialistService specialistService;
	@GET
	public Response getAllSpecialists(){
		return specialistService.listAll();
	}
	
	@GET
	@Path("{id}")
	public Specialist getOneSpecialist(@PathParam("id") Long id){
		return specialistService.findById(id);
	}
	
	@POST
	public Response createSpecialist(Specialist newSpecialist) {
		return specialistService.create(newSpecialist);
	}
	
	@PUT
	@Path("{id}")
	public Response updateSpecialist(@PathParam("id") Long id, Specialist editSpecialist) {
		return specialistService.edit(id, editSpecialist);
	}
	
	@DELETE
	@Path("{id}")
	public Response deleteSpecialist(@PathParam("id") Long id) {
		return specialistService.delete(id);
	}
	
	
}
