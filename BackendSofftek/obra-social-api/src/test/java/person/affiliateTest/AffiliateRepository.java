package person.affiliateTest;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class AffiliateRepository {
    @InjectMock
    AffiliateRepository affiliateRepository;


}
