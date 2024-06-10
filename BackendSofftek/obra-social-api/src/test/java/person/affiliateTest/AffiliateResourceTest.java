package person.affiliateTest;


import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import person.controller.AffiliateResource;
import person.dto.AffiliateDTO;
import person.model.Affiliate;
import person.model.Role;
import person.service.AffiliateService;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@QuarkusTest
public class AffiliateResourceTest {
    @InjectMock
    AffiliateService affiliateService;

    @Inject
    AffiliateResource affiliateResource;

    private AffiliateDTO affiliateDTO;

    @BeforeEach
    void instanceAffiliateDTO(){
        affiliateDTO = new AffiliateDTO();
        affiliateDTO.setDni("84201849");
        affiliateDTO.setLastName("noa");
        affiliateDTO.setFirstName("nahuel");
        affiliateDTO.setHealthInsuranceCode("72814724");
    }

    @Test
    public void testGetAllAffiliateResource(){
        List<AffiliateDTO> ltsAffiliateDTO = List.of(affiliateDTO);

        // Crea una simulación de AffilaiteRepository que retorna el listado de afiliados
        Mockito.when(affiliateResource.getAllAffiliates()).thenReturn(Response.ok(ltsAffiliateDTO).build());
        Response response = affiliateResource.getAllAffiliates();

        //verifico que la respeusta obtenida sea la esperada
        assertEquals(Response.ok().build().getStatus(),response.getStatus());

        //obtengo la entidad pasada por la response
        List<AffiliateDTO> entity = (List<AffiliateDTO>) response.getEntity();
        assertFalse(entity.isEmpty());

        // Verifica las propiedades del primer afiliado en la lista
        assertEquals("nahuel", entity.get(0).getFirstName());
        assertEquals("noa", entity.get(0).getLastName());
        assertEquals("84201849",entity.get(0).getDni());
        assertEquals("72814724", entity.get(0).getHealthInsuranceCode());
    }

    @Test
    public void testGetByIdAffiliateResource(){
        // Crea una simulación de AffilaiteRepository que retorna el listado de afiliados
        Mockito.when(affiliateResource.getAffiliate(1L)).thenReturn(Response.ok(affiliateDTO).build());
        Response response = affiliateResource.getAffiliate(1L);

        //verifico que la respeusta obtenida sea la esperada
        assertEquals(Response.ok().build().getStatus(),response.getStatus());

        //obtengo la entidad pasada por la response
        AffiliateDTO entity = response.readEntity(AffiliateDTO.class);
        assertFalse(entity==null);

        // Verifica las propiedades del primer afiliado en la lista
        assertEquals("nahuel", entity.getFirstName());
        assertEquals("noa", entity.getLastName());
        assertEquals("84201849",entity.getDni());
        assertEquals("72814724", entity.getHealthInsuranceCode());
    }

    @Test
    public void testCreateAffiliateResource(){
        // Crea una simulación de AffilaiteRepository que retorna el listado de afiliados
        Mockito.when(affiliateResource.createAffiliate(affiliateDTO)).thenReturn(Response.status(Response.Status.CREATED).entity(affiliateDTO).build());
        Response response = affiliateResource.createAffiliate(affiliateDTO);

        //verifico que la respeusta obtenida sea la esperada
        assertEquals(Response.status(Response.Status.CREATED).build().getStatus(),response.getStatus());

        //obtengo la entidad pasada por la response
        AffiliateDTO entity = response.readEntity(AffiliateDTO.class);
        assertFalse(entity==null);

        // Verifica las propiedades del afiliado
        assertEquals("nahuel", entity.getFirstName());
        assertEquals("noa", entity.getLastName());
        assertEquals("84201849",entity.getDni());
        assertEquals("72814724", entity.getHealthInsuranceCode());
    }


    @Test
    public void testEditAffiliateResource(){
        // Crea una simulación de AffilaiteRepository que retorna el listado de afiliados
        affiliateDTO.setLastName("nico");

        Mockito.when(affiliateResource.updateAffiliate(1L,affiliateDTO)).thenReturn(Response.ok(affiliateDTO).build());
        Response response = affiliateResource.updateAffiliate(1L,affiliateDTO);

        //verifico que la respeusta obtenida sea la esperada
        assertEquals(Response.ok().build().getStatus(),response.getStatus());
        //obtengo la entidad pasada por la response
        AffiliateDTO entity = response.readEntity(AffiliateDTO.class);
        assertFalse(entity==null);

        // Verifica las propiedades del afiliado
        assertEquals("nahuel", entity.getFirstName());
        assertEquals("nico", entity.getLastName());
        assertEquals("84201849",entity.getDni());
        assertEquals("72814724", entity.getHealthInsuranceCode());
    }

    @Test
    public void testDeleteAffiliateResource(){
        // Crea una simulación de AffilaiteRepository que retorna el listado de afiliados
        affiliateDTO.setLastName("nico");
        Mockito.when(affiliateResource.deleteAffiliateById(1L)).thenReturn(Response.ok("se elimino con exito").build());
        Response response = affiliateResource.deleteAffiliateById(1L);
        //verifico que la respeusta obtenida sea la esperada
        assertEquals(Response.ok().build().getStatus(),response.getStatus());
    }
}
