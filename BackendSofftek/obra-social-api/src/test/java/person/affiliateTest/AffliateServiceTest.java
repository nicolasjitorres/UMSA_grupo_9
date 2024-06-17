//package person.affiliateTest;
//
//import io.quarkus.test.InjectMock;
//import io.quarkus.test.junit.QuarkusTest;
//import jakarta.ws.rs.core.Response;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import person.dto.AffiliateDTO;
//import person.service.AffiliateService;
//import java.util.List;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@QuarkusTest
//public class AffliateServiceTest {
//
//    @InjectMock
//    AffiliateService affiliateService;
//
//    private AffiliateDTO affiliateDTO;
//
//    @BeforeEach
//    void instanceAffiliateDTO(){
//        affiliateDTO = new AffiliateDTO();
//        affiliateDTO.setDni("84201849");
//        affiliateDTO.setLastName("noa");
//        affiliateDTO.setFirstName("nahuel");
//        affiliateDTO.setHealthInsuranceCode("72814724");
//    }
//
//    @Test
//    public void testGetAllAffiliateService() {
//        List<AffiliateDTO> ltsAffilaite = List.of(affiliateDTO);
//
//        // Configurar el comportamiento del mock
//        Mockito.when(affiliateService.getAffiliates()).thenReturn(Response.ok(ltsAffilaite).build());
//
//        // Llamar al método y obtener la respuesta
//        Response response = affiliateService.getAffiliates();
//
//        // Verificar que el código de estado es 200
//        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
//
//        // Verificar que el contenido de la respuesta es el esperado
//        assertEquals(ltsAffilaite, response.getEntity());
//    }
//
//    @Test
//    public void testGetByIdAffiliateService() {
//        // Configurar el comportamiento del mock
//        Mockito.when(affiliateService.getAffiliateById(1L)).thenReturn(Response.ok(affiliateDTO).build());
//
//        // Llamar al método y obtener la respuesta
//        Response response = affiliateService.getAffiliateById(1L);
//
//        // Verificar que el código de estado es 200
//        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
//
//        // Verificar que el contenido de la respuesta es el esperado
//        assertEquals(affiliateDTO, response.getEntity());
//    }
//
//    @Test
//    public void testCreateAffiliateService() {
//        // Configurar el comportamiento del mock
//        Mockito.when(affiliateService.addAffiliate(affiliateDTO)).thenReturn(Response.status(Response.Status.CREATED).build());
//        // Llamar al método y obtener la respuesta
//        Response response = affiliateService.addAffiliate(affiliateDTO);
//        // Verificar que el código de estado es 201/CREATED
//        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
//    }
//
//    @Test
//    public void testUpdateAffiliateService() {
//        affiliateDTO.setDni("32145");
//        // Configurar el comportamiento del mock
//        Mockito.when(affiliateService.editAffiliate(1L,affiliateDTO)).thenReturn(Response.ok(affiliateDTO).build());
//        // Llamar al método y obtener la respuesta
//        Response response = affiliateService.editAffiliate(1L,affiliateDTO);
//        // Verificar que el código de estado es 201/CREATED
//        assertEquals(Response.ok().build().getStatus(), response.getStatus());
//    }
//
//    @Test
//    public void testDeleteAffiliateService() {
//        // Configurar el comportamiento del mock
//        Mockito.when(affiliateService.deleteAffiliate(1L)).thenReturn(Response.ok().build());
//        // Llamar al método y obtener la respuesta
//        Response response = affiliateService.deleteAffiliate(1L);
//        // Verificar que el código de estado es 201/CREATED
//        assertEquals(Response.ok().build().getStatus(), response.getStatus());
//    }
//}
