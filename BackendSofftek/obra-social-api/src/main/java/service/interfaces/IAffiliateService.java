package service.interfaces;

import java.util.List;

import model.Affiliate;

public interface IAffiliateService {
    public List<Affiliate> getAllAffiliates();
    public Affiliate getAffiliateById(Long id);
    public Affiliate addAffiliate(Affiliate newAffiliate);
    public Affiliate deleteAffiliate(Long id);
    public Affiliate editAffiliate(Long id, Affiliate newAffiliate);
}
