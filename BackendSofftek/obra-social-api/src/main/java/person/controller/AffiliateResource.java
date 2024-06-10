package person.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import person.dto.AffiliateDTO;
import person.model.Affiliate;
import person.service.IAffiliateService;

import java.util.ArrayList;
import java.util.List;

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
		List<Affiliate> affiliates = affiliateService.getAffiliates();
		List<AffiliateDTO> affiliateDTOs = new ArrayList<>();
		for (Affiliate affiliate : affiliates) {
			affiliateDTOs.add(convertToDTO(affiliate));
		}
		return Response.ok(affiliateDTOs).build();
	}
	
	@GET
	@Path("{id}")
	public Response getAffiliate(@PathParam("id") Long id){
		Affiliate affiliate = affiliateService.getAffiliateById(id);
		if (affiliate != null) {
			return Response.ok(convertToDTO(affiliate)).build();
		} else {
			return Response.status(404).build();
		}
	}
	
	@POST
	public Response createAffiliate(Affiliate newAffiliate) {
		affiliateService.addAffiliate(newAffiliate);
		return Response.status(Response.Status.CREATED).entity(convertToDTO(newAffiliate)).build();
	}

	@PUT
	@Path("{id}")
	public Response updateAffiliate(@PathParam("id") Long id, Affiliate editAffiliate) {
		Affiliate existingAffiliate = affiliateService.getAffiliateById(id);
		if (existingAffiliate != null) {
			affiliateService.editAffiliate(id, editAffiliate);
			return Response.ok(convertToDTO(editAffiliate)).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}



	@DELETE
	@Path("{id}")
	public Response deleteAffiliateById(@PathParam("id") Long id) {
		Affiliate existingAffiliate = affiliateService.getAffiliateById(id);
		if (existingAffiliate != null) {
			affiliateService.deleteAffiliate(id);
			return Response.ok("Se eliminó con exito").build();
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
