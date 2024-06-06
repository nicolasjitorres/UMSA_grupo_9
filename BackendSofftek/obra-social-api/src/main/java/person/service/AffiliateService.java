package person.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import person.model.Affiliate;
import person.repository.AffiliateRepository;

@ApplicationScoped
public class AffiliateService {

	@Inject
	private AffiliateRepository affiliateRepository;
	
	public Response listAll() {
		return Response.ok(affiliateRepository.listAll())
				.build();
	}
	
	public Response listOne(Long id) {
		Affiliate affiliate = affiliateRepository.findById(id);
		if (affiliate == null) {
			return Response.status(400)
					.entity("El especialista con id " + id + " no existe.")
					.build();
		} else {
		return Response.ok(affiliate)
				.build();	
		}
	}
	
	public Response create(Affiliate newAffiliate) {
		affiliateRepository.persist(newAffiliate);
		return Response.ok(newAffiliate)
			.build();
	}
	
	public Response edit(Long id,Affiliate editedAffiliate) {
		Affiliate affiliate = affiliateRepository.findById(id);
		if (affiliate == null) {
			return Response.status(400)
					.entity("No existe el especialista.")
					.build();
		} else {
			affiliate.setFirstName(editedAffiliate.getFirstName());
			affiliate.setLastName(editedAffiliate.getLastName());
			affiliate.setDni(editedAffiliate.getDni());
			
			return Response.ok(editedAffiliate)
					.build();
		}
	}
	
	public Response delete(Long id) {
		if (affiliateRepository.deleteById(id)) {
			return Response.ok("Se eliminó el afiliado correctamente.")
					.build();
		} else {
			return Response.status(400)
					.entity("No se pudo eliminar el afiliado.")
					.build();
		}
	}
	
}
