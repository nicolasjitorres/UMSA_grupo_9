package resource;

import mappers.Mapper;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import model.Affiliate;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import service.interfaces.IAffiliateService;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/afiliados")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Gestion de afiliados", description = "Métodos relacionados con la gestión de afiliados."
		+ "Mediante estos métodos podemos realizar una correcta gestión de los afiliados de la obra social.")
public class AffiliateResource {

	@Inject
	private IAffiliateService affiliateService;

	@GET
	@Operation(summary = "Obtener todos los afiliados", description = "Retorna una lista de todos los afiliados de la obra social.")
	@APIResponses(value = { @APIResponse(responseCode = "200", description = "Afiliados obtenidos con éxito"),
			@APIResponse(responseCode = "204", description = "No hay afiliados cargados en el sistema") })
	public Response getAllAffiliates() {
		List<Affiliate> affiliates = affiliateService.getAllAffiliates();
		if (!affiliates.isEmpty()) {
			return Response.ok(Mapper.toAffiliateDTOList(affiliates)).build();
		} else {
			return Response.status(204).build();
		}
	}

	@GET
	@Path("{id}")
	@Operation(summary = "Obtener un afiliado por ID", description = "Retorna un afiliado basado en el ID proporcionado.")
	@APIResponses(value = { @APIResponse(responseCode = "200", description = "Afiliado obtenido con éxito"),
			@APIResponse(responseCode = "404", description = "Afiliado no encontrado") })

	public Response getAffiliate(@PathParam("id") Long id) {
		Affiliate affiliate = affiliateService.getAffiliateById(id);
		if (affiliate == null) {
			return Response.status(Status.NOT_FOUND).entity("No existe afiliado con el id " + id).build();
		} else {
			return Response.ok(Mapper.toAffiliateDTO(affiliate)).build();
		}
	}

	@POST
	@Operation(summary = "Crear un afiliado", description = "Crea un nuevo afiliado.")
	@APIResponse(responseCode = "200", description = "Afiliado creado con éxito")
	@APIResponse(responseCode = "400", description = "Solicitud incorrecta, hay datos invalidos")
	public Response createAffiliate(Affiliate newAffiliate) {
		try {
			Affiliate affiliate = affiliateService.addAffiliate(newAffiliate);
			return Response.ok(Mapper.toAffiliateDTO(affiliate)).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@PUT
	@Path("{id}")
	@Operation(summary = "Actualiza un afiliado", description = "Actualiza un afiliado cuyo ID existe en el sistema, no se pueden ingresar datos vacios.")
	@APIResponse(responseCode = "200", description = "Afiliado actualizado con éxito")
	@APIResponse(responseCode = "400", description = "Solicitud incorrecta, hay datos invalidos")
	@APIResponse(responseCode = "404", description = "Solicitud incorrecta, no existe el afiliado en el sistema con la ID ingresada")

	public Response updateAffiliate(@PathParam("id") Long id, Affiliate editAffiliateDTO) {
		try {
			Affiliate updatedAffiliate = affiliateService.editAffiliate(id,editAffiliateDTO);
			return Response.ok(Mapper.toAffiliateDTO(updatedAffiliate)).build();
		} catch (IllegalArgumentException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		} catch (Exception e) {
			return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
		}
	}

	@DELETE
	@Path("{id}")
	@Operation(summary = "Eliminar un afiliado", description = "Elimina un afiliado existente.")
	@APIResponses(value = { @APIResponse(responseCode = "204", description = "Afiliado eliminado con éxito"),
			@APIResponse(responseCode = "404", description = "Afiliado no encontrado") })
	public Response deleteAffiliateById(@PathParam("id") Long id) {
		try {
			Affiliate deletedAffiliate = affiliateService.deleteAffiliate(id);
			return Response.status(204).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}
}
