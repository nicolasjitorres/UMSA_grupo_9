//package person.affiliateTest;
//
//import io.quarkus.hibernate.orm.panache.PanacheQuery;
//import io.quarkus.test.InjectMock;
//import io.quarkus.test.junit.QuarkusTest;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import person.model.Affiliate;
//import person.model.Role;
//import person.model.Specialist;
//import person.model.Speciality;
//import person.repository.AffiliateRepository;
//import person.repository.SpecialistRepository;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//
//@QuarkusTest
//public class AffiliateRepositoryTest {
//    @InjectMock
//    AffiliateRepository affiliateRepository;
//
//    private Affiliate affiliate;
//
//    @BeforeEach
//    void inject(){
//        affiliate = new Affiliate();
//        affiliate.setDni("84201849");
//        affiliate.setLastName("noa");
//        affiliate.setFirstName("nahuel");
//        affiliate.setHealthInsuranceCode("72814724");
//        affiliate.setRole(Role.USER);
//    }
//
//    @Test
//    public void testListAllffiliate(){
//        List<Affiliate> ltsAffiliate = List.of(affiliate);
//
//        // Crea una simulación de AffilaiteRepository que retorna el listado de afiliados
//        Mockito.when(affiliateRepository.listAll()).thenReturn(ltsAffiliate);
//        List<Affiliate> entity = affiliateRepository.listAll();
//        assertFalse(entity.isEmpty());
//
//        // Verifica las propiedades del primer afiliado en la lista
//        assertEquals("nahuel", entity.get(0).getFirstName());
//        assertEquals("noa", entity.get(0).getLastName());
//        assertEquals(Role.USER, entity.get(0).getRole());
//        assertEquals("84201849",entity.get(0).getDni());
//        assertEquals("72814724", entity.get(0).getHealthInsuranceCode());
//    }
//
//
//    @Test
//    public void testFindByIDffiliate(){
//
//        // Crea una simulación de AffilaiteRepository que retorna el listado de afiliados
//        Mockito.when(affiliateRepository.findById(1L)).thenReturn(affiliate);
//        Affiliate entity = affiliateRepository.findById(1L);
//        assertFalse(entity == null);
//
//        // Verifica las propiedades del afiliado
//        assertEquals("nahuel", entity.getFirstName());
//        assertEquals("noa", entity.getLastName());
//        assertEquals(Role.USER, entity.getRole());
//        assertEquals("84201849",entity.getDni());
//        assertEquals("72814724", entity.getHealthInsuranceCode());
//    }
//
//    @Test
//    public void testMergeSpecialist(){
//        //instanciamos uan simulacion de AffiliateRepository
//        AffiliateRepository affiliateMockRepository = Mockito.mock(AffiliateRepository.class);
//        affiliate.setId(1L);
//        //y persistimos los datos
//        affiliateMockRepository.persist(affiliate);
//        affiliate.setLastName("richadns");
//        affiliateMockRepository.persist(affiliate);
//        verify(affiliateMockRepository,times(2)).persist(affiliate);
//    }
//
//    @Test
//    public void testPersistSpecialist(){
//        AffiliateRepository affiliateMockRepository = Mockito.mock(AffiliateRepository.class);
//        affiliateMockRepository.persist(affiliate);
//        verify(affiliateMockRepository).persist(affiliate);
//    }
//
//    @Test
//    public void testSpecialistDelete() {
//        AffiliateRepository affiliateMockRepository = Mockito.mock(AffiliateRepository.class);
//        affiliate.setId(1L);
//        affiliateMockRepository.delete(affiliate);
//        verify(affiliateMockRepository).delete(affiliate);
//    }
//
//}
