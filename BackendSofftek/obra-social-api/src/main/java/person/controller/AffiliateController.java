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
import person.model.Affiliate;
import person.service.AffiliateService;

@Path("/afiliado")
@Transactional
@Produces(MediaType.APPLICATION_JSON)
public class AffiliateController {
	
	private AffiliateService affiliateService;
	
	@Inject
	public AffiliateController(AffiliateService affiliateService) {
		this.affiliateService = affiliateService;
	}

	@GET
	public Response getAllSpecialists(){
		return affiliateService.listAll();
	}
	
	@GET
	@Path("{id}")
	public Response getOneSpecialist(@PathParam("id") Long id){
		return affiliateService.listOne(id);
	}
	
	@POST
	public Response createSpecialist(Affiliate newAffiliate) {
		System.out.println(newAffiliate);
		return affiliateService.create(newAffiliate);
	}
	
	@PUT
	@Path("{id}")
	public Response updateSpecialist(@PathParam("id") Long id, Affiliate editAffiliate) {
		return affiliateService.edit(id, editAffiliate);
	}
	
	@DELETE
	@Path("{id}")
	public Response deleteSpecialist(@PathParam("id") Long id) {
		return affiliateService.delete(id);
	}
	
	
}
