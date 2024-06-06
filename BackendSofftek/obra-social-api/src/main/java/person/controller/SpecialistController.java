package person.controller;


import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import person.model.Specialist;
import person.service.SpecialistService;

@Path("/especialista")
@Transactional
@Produces(MediaType.APPLICATION_JSON)
public class SpecialistController {
	
	private SpecialistService specialistService;
	
	@Inject
	public SpecialistController(SpecialistService specialistService) {
		this.specialistService = specialistService;
	}

	@GET
	public Response getAllSpecialists(){
		return specialistService.listAll();
	}
	
	@GET
	@Path("{id}")
	public Response getOneSpecialist(@PathParam("id") Long id){
		return specialistService.listOne(id);
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
	public Response deleteSpecialist(@PathParam("id") Long id, Specialist editSpecialist) {
		return specialistService.delete(id);
	}
	
	
}
