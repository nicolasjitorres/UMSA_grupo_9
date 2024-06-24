package service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import model.Affiliate;
import model.Shift;
import repository.AffiliateRepository;
import service.interfaces.IAffiliateService;
import validator.AffiliateValidator;

import java.util.List;

import dto.mappers.AffiliateMapper;

@ApplicationScoped
@Transactional
public class AffiliateService implements IAffiliateService {

	@Inject
	private AffiliateRepository affiliateRepository;

	@Inject
	private AffiliateValidator affiliateValidator;

	@Inject
	private ShiftService shiftService;

	@Override
	public List<Affiliate> getAllAffiliates() {
		return affiliateRepository.listAll();
	}

	@Override
	public Affiliate getAffiliateById(Long id) {
		return affiliateRepository.findById(id);
	}

	@Override

	public Affiliate addAffiliate(Affiliate newAffiliate) throws Exception {
		if (affiliateRepository.findByDni(newAffiliate.getDni()) != null)
			throw new IllegalArgumentException("Ya existe un afiliado con el dni: " + newAffiliate.getDni());
		if (affiliateRepository.findByHealthInsuranceCode(newAffiliate.getHealthInsuranceCode()) != null)
			throw new IllegalArgumentException(
					"Ya existe un afiliado con el codigo de obra social: " + newAffiliate.getHealthInsuranceCode());

		List<String> existInvalidData = affiliateValidator.validateAffiliate(newAffiliate);
		if(existInvalidData!=null) throw new IllegalArgumentException(existInvalidData.toString());
		affiliateRepository.persist(newAffiliate);
		return newAffiliate;
	}

	@Override
	public Affiliate editAffiliate(Long id, @Valid Affiliate affiliate) throws Exception {
		Affiliate existingAffiliate = affiliateRepository.findById(id);
		if (existingAffiliate == null)
			throw new Exception("El afiliado con id " + id + " no existe.");
		List<String> existInvalidData = affiliateValidator.validateAffiliate(affiliate);
		if(existInvalidData!=null) throw new IllegalArgumentException(existInvalidData.toString());

		existingAffiliate.setFirstName(affiliate.getFirstName());
		existingAffiliate.setLastName(affiliate.getLastName());
		existingAffiliate.setDni(affiliate.getDni());
		existingAffiliate.setHealthInsuranceCode(affiliate.getHealthInsuranceCode());
		existingAffiliate.setEmail((affiliate.getEmail()));
		affiliateRepository.persistAndFlush(existingAffiliate);
		return existingAffiliate;
	}

	@Override
	public Affiliate deleteAffiliate(Long id) throws Exception {
		Affiliate existingAffiliate = affiliateRepository.findById(id);
		if (existingAffiliate == null)
			throw new Exception("El afiliado con id " + id + " no existe.");

		if(!shiftService.getShiftByAffiliateId(id).isEmpty())throw new Exception("No se puede borrar el afiliado con ID: "+id+" debido a que está asociado a un turno.");
		affiliateRepository.deleteById(id);
		return existingAffiliate;
	}
}
