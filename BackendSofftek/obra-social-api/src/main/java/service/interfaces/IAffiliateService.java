package service.interfaces;

import jakarta.ws.rs.core.Response;
import dto.AffiliateDTO;
import model.Affiliate;

public interface IAffiliateService {
    public Affiliate findAffiliates();
    public Affiliate getAffiliateById(Long id);
    public Affiliate addAffiliate(AffiliateDTO newAffiliateDTO);
    public Affiliate deleteAffiliate(Long id);
    public Affiliate editAffiliate(Long id, AffiliateDTO newAffiliateDTO);
}
