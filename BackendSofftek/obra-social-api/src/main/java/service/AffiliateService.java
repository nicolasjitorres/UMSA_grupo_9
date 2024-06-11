package service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import dto.AffiliateDTO;
import model.Affiliate;
import repository.AffiliateRepository;
import service.interfaces.IAffiliateService;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Transactional
public class AffiliateService implements IAffiliateService {
	@Inject
	private AffiliateRepository affiliateRepository;
	@Override
	public Affiliate findAffiliates() {
		List<AffiliateDTO> affiliateDTOs = new ArrayList<>();
		for (Affiliate affiliate : affiliateRepository.listAll()) {
			affiliateDTOs.add(convertEntityToDTO(affiliate));
		}
		return null;
	}
	@Override
	public Affiliate getAffiliateById(Long id) {
		return null;
	}
	@Override
	public Affiliate addAffiliate(AffiliateDTO newAffiliateDTO) {
		Affiliate newAffiliate = this.convertDTOToEntity(newAffiliateDTO);
		affiliateRepository.persist(newAffiliate);
		return null;
	}
	@Override
	public Affiliate editAffiliate(Long id, AffiliateDTO editedAffiliateDTO) {
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
		return null;
	}
	@Override
	public Affiliate deleteAffiliate(Long id) {
		affiliateRepository.deleteById(id);
		return null;
	}


	public Affiliate convertDTOToEntity(AffiliateDTO dto) {
		if (dto == null) {
			return null;
		}
		Affiliate entity = new Affiliate();
		entity.setId(dto.getId());
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
