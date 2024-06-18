package service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import model.Affiliate;
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
			throw new Exception("Ya existe un afiliado con el dni: " + newAffiliate.getDni());
		if (affiliateRepository.findByHealthInsuranceCode(newAffiliate.getHealthInsuranceCode()) != null)
			throw new Exception(
					"Ya existe un afiliado con el codigo de obra social: " + newAffiliate.getHealthInsuranceCode());
		List<String> existingErrors = affiliateValidator.validateAffiliate(AffiliateMapper.entityToDto(newAffiliate));
		if (existingErrors != null)
			throw new IllegalArgumentException(existingErrors.toString());
		affiliateRepository.persist(newAffiliate);
		return newAffiliate;

	}

	@Override
	public Affiliate editAffiliate(Long id, Affiliate editedAffiliate) throws Exception {
		Affiliate existingAffiliate = affiliateRepository.findById(id);
		if (existingAffiliate == null)
			throw new Exception("El afiliado con id " + id + " no existe.");
		List<String> existingErrors = affiliateValidator
				.validateAffiliate(AffiliateMapper.entityToDto(editedAffiliate));
		if (existingErrors != null)
			throw new IllegalArgumentException(existingErrors.toString());

		existingAffiliate.setFirstName(editedAffiliate.getFirstName());
		existingAffiliate.setLastName(editedAffiliate.getLastName());
		existingAffiliate.setDni(editedAffiliate.getDni());
		existingAffiliate.setHealthInsuranceCode(editedAffiliate.getHealthInsuranceCode());
		affiliateRepository.persistAndFlush(existingAffiliate);
		return existingAffiliate;
	}

	@Override
	public Affiliate deleteAffiliate(Long id) throws Exception {
		Affiliate existingAffiliate = affiliateRepository.findById(id);
		if (existingAffiliate == null)
			throw new Exception("El afiliado con id " + id + " no existe.");

		affiliateRepository.deleteById(id);
		return existingAffiliate;
	}
}