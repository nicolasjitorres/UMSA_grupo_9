package service;

import java.util.Optional;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import model.Affiliate;
import model.Specialist;
import model.User;
import repository.AffiliateRepository;
import repository.SpecialistRepository;

@ApplicationScoped
public class UserService {
    
    @Inject
    private SpecialistRepository specialistRepository;

    @Inject
    private AffiliateRepository affiliateRepository;

    public Optional<User> findByEmail(String email) {
        Optional<Specialist> specialist = specialistRepository.findByEmail(email);
        if (specialist.isPresent()) {
            return Optional.of(specialist.get());
        }

        Optional<Affiliate> affiliate = affiliateRepository.findByEmail(email);
        if (affiliate.isPresent()) {
            return Optional.of(affiliate.get());
        }

        return Optional.empty();
    }
}
