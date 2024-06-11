package resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import dto.AffiliateDTO;
import dto.mappers.AffiliateMapper;
import model.Affiliate;
import service.interfaces.IAffiliateService;

import java.util.List;
import java.util.stream.Collectors;

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
		List<Affiliate> affiliates = affiliateService.getAllAffiliates();
		List<AffiliateDTO> affiliateDTOs = affiliates.stream()
				.map(AffiliateMapper::entityToDto)
				.collect(Collectors.toList());
		return Response.ok(affiliateDTOs).build();
	}
	
	@GET
	@Path("{id}")
	public Response getAffiliate(@PathParam("id") Long id){
		Affiliate affiliate = affiliateService.getAffiliateById(id);
		if (affiliate == null) {
			return Response.status(Status.NOT_FOUND).entity("No existe afiliado con el id " + id).build();
		} else {
			return Response.ok(AffiliateMapper.entityToDto(affiliate)).build();
		}
	}
	
	@POST
	public Response createAffiliate(AffiliateDTO newAffiliateDTO) {
		Affiliate newAffiliate = AffiliateMapper.dtoToEntity(newAffiliateDTO);
		try {
			Affiliate affiliate = affiliateService.addAffiliate(newAffiliate);
			return Response.status(Response.Status.CREATED).entity(AffiliateMapper.entityToDto(affiliate)).build();		
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@PUT
	@Path("{id}")
	public Response updateAffiliate(@PathParam("id") Long id, AffiliateDTO editAffiliateDTO) {
		try {
			Affiliate updatedAffiliate =  affiliateService.editAffiliate(id, AffiliateMapper.dtoToEntity(editAffiliateDTO));
			return Response.ok(AffiliateMapper.entityToDto(updatedAffiliate)).build();
		} catch (Exception e) {
			return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
		}
	}

	@DELETE
	@Path("{id}")
	public Response deleteAffiliateById(@PathParam("id") Long id) {
		try {
			Affiliate deletedAffiliate = affiliateService.deleteAffiliate(id);
			return Response.status(Response.Status.OK).entity(AffiliateMapper.entityToDto(deletedAffiliate)).build();
		} catch (Exception e) {
			return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
		}
	}
}
