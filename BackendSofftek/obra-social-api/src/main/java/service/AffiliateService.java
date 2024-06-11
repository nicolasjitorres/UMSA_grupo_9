package service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import model.Affiliate;
import repository.AffiliateRepository;
import service.interfaces.IAffiliateService;

import java.util.List;

@ApplicationScoped
@Transactional
public class AffiliateService implements IAffiliateService {
	
	@Inject
	private AffiliateRepository affiliateRepository;
	
	@Override
	public List<Affiliate> getAllAffiliates() {
		return affiliateRepository.listAll();
	}
	
	@Override
	public Affiliate getAffiliateById(Long id) {
		return affiliateRepository.findById(id);
	}
	
	@Override
	public Affiliate addAffiliate(Affiliate newAffiliate) throws Exception{
//		Validaciones
		if (newAffiliate.getFirstName() != null) {
			affiliateRepository.persist(newAffiliate);			
			return newAffiliate; 
		} else {
			throw new Exception("El campo nombre debe contener algo.");
		}
	}
	
	@Override
	public Affiliate editAffiliate(Long id, Affiliate editedAffiliate) throws Exception{
		Affiliate affiliate = affiliateRepository.findById(id);
		if (affiliate != null) {
			if (editedAffiliate.getFirstName() != null) {
				affiliate.setFirstName(editedAffiliate.getFirstName());
			}
			if (editedAffiliate.getLastName() != null) {
				affiliate.setLastName(editedAffiliate.getLastName());
			}
			if (editedAffiliate.getDni() != null) {
				affiliate.setDni(editedAffiliate.getDni());
			}
			if (editedAffiliate.getHealthInsuranceCode() != null) {
				affiliate.setHealthInsuranceCode(editedAffiliate.getHealthInsuranceCode());
			}
			
//			Agregar datos faltantes
			
//			affiliateRepository.persist(affiliate);
			affiliateRepository.getEntityManager().merge(affiliate);
			return affiliateRepository.findById(id);
		} else {
			throw new Exception("El afiliado con id " + id + " no existe.");
		}
	}
	
	@Override
	public Affiliate deleteAffiliate(Long id) throws Exception{
		Affiliate existingAffiliate = affiliateRepository.findById(id);
		if (existingAffiliate != null) {
			affiliateRepository.deleteById(id);		
			return existingAffiliate;
		} else {
			throw new Exception("El afiliado con id " + id + " no existe.");
		}
	}
}
