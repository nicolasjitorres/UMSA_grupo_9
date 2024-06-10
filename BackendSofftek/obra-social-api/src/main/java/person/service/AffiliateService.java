package person.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import person.dto.AffiliateDTO;
import person.model.Affiliate;
import person.repository.AffiliateRepository;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Transactional
public class AffiliateService implements IAffiliateService {
	@Inject
	private AffiliateRepository affiliateRepository;
	@Override
	public Response getAffiliates() {
		List<AffiliateDTO> affiliateDTOs = new ArrayList<>();
		for (Affiliate affiliate : affiliateRepository.listAll()) {
			affiliateDTOs.add(convertEntityToDTO(affiliate));
		}
		return Response.ok(affiliateDTOs).build();
	}
	@Override
	public Response getAffiliateById(Long id) {
		return Response.ok(this.convertEntityToDTO(affiliateRepository.findById(id))).build();
	}
	@Override
	public Response addAffiliate(AffiliateDTO newAffiliateDTO) {
		Affiliate newAffiliate = this.convertDTOToEntity(newAffiliateDTO);
		affiliateRepository.persist(newAffiliate);
		return Response.status(Response.Status.CREATED).entity(this.convertEntityToDTO(newAffiliate)).build();
	}
	@Override
	public Response editAffiliate(Long id, AffiliateDTO editedAffiliateDTO) {
		Affiliate affiliate = affiliateRepository.findById(id);
		if (affiliate != null) {
			if (editedAffiliateDTO.getFirstName() != null) {
				affiliate.setFirstName(editedAffiliateDTO.getFirstName());
			}
			if (editedAffiliateDTO.getLastName() != null) {
				affiliate.setLastName(editedAffiliateDTO.getLastName());
			}
			if (editedAffiliateDTO.getDni() != null) {
				affiliate.setDni(editedAffiliateDTO.getDni());
			}
			if (editedAffiliateDTO.getHealthInsuranceCode() != null) {
				affiliate.setHealthInsuranceCode(editedAffiliateDTO.getHealthInsuranceCode());
			}
		}
		affiliateRepository.persist(affiliate);
		return Response.ok(this.convertEntityToDTO(affiliate)).build();
	}
	@Override
	public Response deleteAffiliate(Long id) {
		affiliateRepository.deleteById(id);
		return Response.ok("se elimino con exito").build();
	}


	public Affiliate convertDTOToEntity(AffiliateDTO dto) {
		if (dto == null) {
			return null;
		}
		Affiliate entity = new Affiliate();
		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
		entity.setHealthInsuranceCode(dto.getHealthInsuranceCode());
		entity.setDni(dto.getDni());
		return entity;
	}

	public AffiliateDTO convertEntityToDTO(Affiliate entity) {
		if (entity == null) {
			return null;
		}
		AffiliateDTO dto = new AffiliateDTO();
		dto.setId(entity.getId());
		dto.setFirstName(entity.getFirstName());
		dto.setLastName(entity.getLastName());
		dto.setHealthInsuranceCode(entity.getHealthInsuranceCode());
		dto.setDni(entity.getDni());
		return dto;
	}
}
