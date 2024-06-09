package person.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import person.model.Affiliate;
import person.repository.AffiliateRepository;

import java.util.List;

@ApplicationScoped
@Transactional
public class AffiliateService implements IAffiliateService {
	@Inject
	private AffiliateRepository affiliateRepository;
	@Override
	public List<Affiliate> getAffiliates() {
		return affiliateRepository.listAll();
	}
	@Override
	public Affiliate getAffiliateById(Long id) {
		return affiliateRepository.findById(id);
	}
	@Override
	public void addAffiliate(Affiliate newAffiliate) {
		affiliateRepository.persist(newAffiliate);

	}
	@Override
	public void editAffiliate(Long id, Affiliate editedAffiliate) {
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
		}
		affiliateRepository.persist(editedAffiliate);
	}
	@Override
	public void deleteAffiliate(Long id) {
		affiliateRepository.deleteById(id);
	}

}
