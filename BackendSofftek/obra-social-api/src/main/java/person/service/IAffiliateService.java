package person.service;

import person.model.Affiliate;
import schedule.model.Schedule;

import java.util.List;

public interface IAffiliateService {
    public List<Affiliate> getAffiliates();
    public Affiliate getAffiliateById(Long id);
    public void addAffiliate(Affiliate newAffiliate);
    public void deleteAffiliate(Long id);
    public void editAffiliate(Long id, Affiliate affiliate);
}
