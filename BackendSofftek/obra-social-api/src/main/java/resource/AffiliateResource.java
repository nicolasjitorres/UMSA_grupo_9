package resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import dto.AffiliateDTO;
import dto.AffiliateMapper;
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
	public Affiliate getAllAffiliates(){
		return affiliateService.findAffiliates();
	}
	
	@GET
	@Path("{id}")
	public Affiliate getAffiliate(@PathParam("id") Long id){
		if (id != null) {
			return affiliateService.getAffiliateById(id);
		} else {
			return null;
		}
	}
	
	@POST
	public Affiliate createAffiliate(AffiliateDTO newAffiliateDTO) {
		return affiliateService.addAffiliate(newAffiliateDTO);
	}

	@PUT
	@Path("{id}")
	public Affiliate updateAffiliate(@PathParam("id") Long id, AffiliateDTO editAffiliateDTO) {
		if (editAffiliateDTO != null) {
			return affiliateService.editAffiliate(id, editAffiliateDTO);
		} else {
			return AffiliateMapper.dtoToEntity(editAffiliateDTO);
		}
	}

	@DELETE
	@Path("{id}")
	public Affiliate deleteAffiliateById(@PathParam("id") Long id) {
		if (id != null) {
			return affiliateService.deleteAffiliate(id);
		} else {
			return null;
		}
	}
}
