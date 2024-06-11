package resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import dto.AffiliateDTO;
import model.Affiliate;
import service.interfaces.IAffiliateService;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/afiliado")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Gestion de afiliados", description = "Métodos relacionados con la gestión de afiliados."
		+ "Mediante estos métodos podemos realizar una correcta gestión de los afiliados de la obra social.")
public class AffiliateResource {

	@Inject
	private IAffiliateService affiliateService;
	@GET
	public Response getAllAffiliates(){
		return affiliateService.getAffiliates();
	}
	
	@GET
	@Path("{id}")
	public Response getAffiliate(@PathParam("id") Long id){
		if (id != null) {
			return affiliateService.getAffiliateById(id);
		} else {
			return Response.status(404).build();
		}
	}
	
	@POST
	public Response createAffiliate(AffiliateDTO newAffiliateDTO) {
		return affiliateService.addAffiliate(newAffiliateDTO);
	}

	@PUT
	@Path("{id}")
	public Response updateAffiliate(@PathParam("id") Long id, AffiliateDTO editAffiliateDTO) {
		if (editAffiliateDTO != null) {
			return affiliateService.editAffiliate(id, editAffiliateDTO);
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@DELETE
	@Path("{id}")
	public Response deleteAffiliateById(@PathParam("id") Long id) {
		if (id != null) {
			return affiliateService.deleteAffiliate(id);
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	private AffiliateDTO convertToDTO(Affiliate affiliate) {
		AffiliateDTO affiliateDTO = new AffiliateDTO();
		affiliateDTO.setId(affiliate.getId());
		affiliateDTO.setFirstName(affiliate.getFirstName());
		affiliateDTO.setLastName(affiliate.getLastName());
		affiliateDTO.setHealthInsuranceCode(affiliate.getHealthInsuranceCode());
		affiliateDTO.setDni(affiliate.getDni());
		return affiliateDTO;
	}


}
