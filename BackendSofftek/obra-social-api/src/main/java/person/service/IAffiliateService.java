package person.service;

import jakarta.ws.rs.core.Response;
import person.dto.AffiliateDTO;
import person.model.Affiliate;
import schedule.model.Schedule;

import java.util.List;

public interface IAffiliateService {
    public Response getAffiliates();
    public Response getAffiliateById(Long id);
    public Response addAffiliate(AffiliateDTO newAffiliateDTO);
    public Response deleteAffiliate(Long id);
    public Response editAffiliate(Long id, AffiliateDTO newAffiliateDTO);
}
