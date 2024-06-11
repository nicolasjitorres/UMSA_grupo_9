package person.specialistTest;


import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.Response;
import model.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import dto.SpecialistDTO;
import model.enums.Speciality;
import service.SpecialistService;
import model.enums.Days;
import model.Schedule;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class SpecialistServiceTest {
    @InjectMock
    SpecialistService specialistService;

    private SpecialistDTO specialistDTO;

    @BeforeEach
    void intanceSpecialistDTO() {
        // Crea un nuevo especialista con sus horarios y ubicación asociados
        specialistDTO = new SpecialistDTO();
        specialistDTO.setFirstName("Noa");
        specialistDTO.setLastName("Nao");
        specialistDTO.setSpeciality(Speciality.DERMATOLOGY.toString());

        // Crea y configura la ubicación del especialista
        Location location = new Location();
        location.setStreet("Avenida Corrientes 456");
        location.setLocality("Buenos Aires");
        location.setProvince("Ciudad Autónoma de Buenos Aires");
        location.setCountry("Argentina");
        specialistDTO.setLocation(location);

        // Crea y configura los horarios del especialista
        Schedule schedule1 = new Schedule();
        schedule1.setStartTime(LocalTime.parse("08:00"));
        schedule1.setEndTime(LocalTime.parse("12:00"));
        schedule1.setDay(Days.SUNDAY);

        Schedule schedule2 = new Schedule();
        schedule2.setStartTime(LocalTime.parse("13:00"));
        schedule2.setEndTime(LocalTime.parse("17:00"));
        schedule2.setDay(Days.WEDNESDAY);

        specialistDTO.setSchedules(Arrays.asList(schedule1, schedule2));
    }

    @Test
    public void testGetAllSpecialistsService() {
        // Ajusta los valores de Specialist según sea necesario
        List<SpecialistDTO> ltsSpecialist = List.of(specialistDTO);

        // Configurar el comportamiento del mock
        Mockito.when(specialistService.listAll()).thenReturn(Response.ok(ltsSpecialist).build());

        // Llamar al método y obtener la respuesta
        Response response = specialistService.listAll();

        // Verificar que el código de estado es 200
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

        // Verificar que el contenido de la respuesta es el esperado
        assertEquals(ltsSpecialist, response.getEntity());
    }

    @Test
    public void testGetSpecialistsService() {
        // Configurar el comportamiento del mock
        Mockito.when(specialistService.findById(1L)).thenReturn(Response.ok(specialistDTO).build());

        // Verificar que el contenido de la respuesta es el esperado
        Response response = specialistService.findById(1L);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

        assertEquals(specialistDTO, response.getEntity());
    }

    @Test
    public void testCreateSpecialistsService() {
        // Configurar el comportamiento del mock
        Mockito.when(specialistService.create(specialistDTO)).thenReturn(Response.status(Response.Status.CREATED).build());
        // Llamar al método y obtener la respuesta
        Response response = specialistService.create(specialistDTO);
        // Verificar que el código de estado es 201/CREATED
        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
    }

    @Test
    public void testUpdateSpecialistsService() {
        specialistDTO.setDni("32145");
        // Configurar el comportamiento del mock
        Mockito.when(specialistService.edit(1L,specialistDTO)).thenReturn(Response.ok().build());

        // Llamar al método y obtener la respuesta
        Response response = specialistService.edit(1L,specialistDTO);
        // Verificar que el código de estado es 201/CREATED
        assertEquals(Response.ok().build().getStatus(), response.getStatus());
    }

    @Test
    public void testDeleteSpecialistsService() {
        // Configurar el comportamiento del mock
        Mockito.when(specialistService.delete(1L)).thenReturn(Response.ok().build());
        // Llamar al método y obtener la respuesta
        Response response = specialistService.delete(1L);
        // Verificar que el código de estado es 201/CREATED
        assertEquals(Response.ok().build().getStatus(), response.getStatus());
    }

}
