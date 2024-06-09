package person;


import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import location.model.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import person.dto.SpecialistDTO;
import person.model.Role;
import person.model.Specialist;
import person.model.Speciality;
import person.service.SpecialistService;
import schedule.model.Days;
import schedule.model.Schedule;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class SpecialistServiceTest {
    @InjectMock
    SpecialistService specialistService;

    private SpecialistDTO specialistDTO;

    private Specialist specialist;

    @BeforeEach
    void intance() {
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

        specialistDTO.setScheduleList(Arrays.asList(schedule1, schedule2));
    }

    @BeforeEach
    void intance2() {
        // Crea un nuevo especialista con sus horarios y ubicación asociados
        specialist = new Specialist();
        specialist.setFirstName("Noa");
        specialist.setLastName("Nao");
        specialist.setSpeciality(Speciality.DERMATOLOGY);
        specialist.setRole(Role.USER);

        // Crea y configura la ubicación del especialista
        Location location = new Location();
        location.setStreet("Avenida Corrientes 456");
        location.setLocality("Buenos Aires");
        location.setProvince("Ciudad Autónoma de Buenos Aires");
        location.setCountry("Argentina");
        specialist.setLocation(location);

        // Crea y configura los horarios del especialista
        Schedule schedule1 = new Schedule();
        schedule1.setStartTime(LocalTime.parse("08:00"));
        schedule1.setEndTime(LocalTime.parse("12:00"));
        schedule1.setDay(Days.SUNDAY);

        Schedule schedule2 = new Schedule();
        schedule2.setStartTime(LocalTime.parse("13:00"));
        schedule2.setEndTime(LocalTime.parse("17:00"));
        schedule2.setDay(Days.WEDNESDAY);

        specialist.setSchedules(Arrays.asList(schedule1, schedule2));
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
        Mockito.when(specialistService.findById(1L)).thenReturn(specialistDTO);
        // Verificar que el contenido de la respuesta es el esperado
        assertEquals(specialistService.findById(1L), specialistDTO);
    }

    @Test
    public void testCreateSpecialistsService() {
        // Configurar el comportamiento del mock
        Mockito.when(specialistService.create(specialist)).thenReturn(Response.status(Response.Status.CREATED).build());
        // Llamar al método y obtener la respuesta
        Response response = specialistService.create(specialist);
        // Verificar que el código de estado es 201/CREATED
        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
    }

    @Test
    public void testUpdateSpecialistsService() {
        // Configurar el comportamiento del mock
        Mockito.when(specialistService.edit(1L,specialist)).thenReturn(Response.ok().build());
        specialist.setId(1L);
        specialist.setLastName("richadns");
        // Llamar al método y obtener la respuesta
        Response response = specialistService.edit(1L,specialist);
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
