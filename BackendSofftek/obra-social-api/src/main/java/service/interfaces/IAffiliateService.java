package service.interfaces;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import model.Affiliate;

@ApplicationScoped
public interface IAffiliateService {
    public List<Affiliate> getAllAffiliates();
    public Affiliate getAffiliateById(Long id);
    public Affiliate addAffiliate(Affiliate newAffiliate) throws Exception;
    public Affiliate deleteAffiliate(Long id) throws Exception;
    public Affiliate editAffiliate(Long id, Affiliate newAffiliate)throws Exception;
}
