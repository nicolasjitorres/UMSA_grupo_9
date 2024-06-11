package resource;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import dto.SpecialistDTO;
import service.SpecialistService;

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
	public Response getOneSpecialist(@PathParam("id") Long id){
		return specialistService.findById(id);
	}
	
	@POST
	public Response createSpecialist(SpecialistDTO newSpecialistDTO) {
		return specialistService.create(newSpecialistDTO);
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
