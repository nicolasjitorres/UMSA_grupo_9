package person;


import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectSpy;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import location.model.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import person.controller.SpecialistResource;
import person.dto.SpecialistDTO;
import person.model.Role;
import person.model.Specialist;
import person.model.Speciality;
import person.repository.SpecialistRepository;
import person.service.SpecialistService;
import schedule.model.Days;
import schedule.model.Schedule;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@QuarkusTest
public class SpecialistResourceTest {

    @InjectMock
    SpecialistRepository specialistRepository;

    @InjectMock
    SpecialistService specialistService;
    @Inject
    SpecialistResource specialistResource;

    private SpecialistDTO specialist;

    @Transactional
    @BeforeEach
    void intance() {
        // Crea un nuevo especialista con sus horarios y ubicación asociados
        specialist = new SpecialistDTO();
        specialist.setId("1");
        specialist.setFirstName("Noa");
        specialist.setLastName("Nao");
        specialist.setSpeciality(Speciality.DERMATOLOGY.toString());

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

        specialist.setScheduleList(Arrays.asList(schedule1, schedule2));
    }

    @Test
    public void testGetAllSpecialistsResource(){
        List<SpecialistDTO> ltsSpecialist = List.of(specialist);

        when(specialistService.listAll()).thenReturn(Response.ok().build());

        Response response = specialistResource.getAllSpecialists();
        assertNotNull(response);
        // Verificar que el código de estado es 200
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertNotNull(response.getEntity());
        List<SpecialistDTO> entity = (List<SpecialistDTO>) response.getEntity();
        // Verificar que el contenido de la respuesta es el esperado
        assertFalse(entity.isEmpty());

        // Verifica las propiedades del primer especialista en la lista
        assertEquals("Noa", entity.get(0).getFirstName());
        assertEquals("Nao", entity.get(0).getLastName());
        assertEquals(Speciality.DERMATOLOGY.toString(), entity.get(0).getSpeciality());

        // Verifica las propiedades de la ubicación del primer especialista en la lista
        Location location = entity.get(0).getLocation();
        assertEquals("Avenida Corrientes 456", location.getStreet());
        assertEquals("Buenos Aires", location.getLocality());
        assertEquals("Ciudad Autónoma de Buenos Aires", location.getProvince());
        assertEquals("Argentina", location.getCountry());

        // Verifica las propiedades de los horarios del primer especialista en la lista
        List<Schedule> schedules = entity.get(0).getScheduleList();
        assertEquals(2, schedules.size()); // Verifica que haya dos horarios

        // Verifica las propiedades del primer horario del primer especialista en la lista
        assertEquals(LocalTime.parse("08:00"), schedules.get(0).getStartTime());
        assertEquals(LocalTime.parse("12:00"), schedules.get(0).getEndTime());
        assertEquals(Days.SUNDAY, schedules.get(0).getDay());

        // Verifica las propiedades del segundo horario del primer especialista en la lista
        assertEquals(LocalTime.parse("13:00"), schedules.get(1).getStartTime());
        assertEquals(LocalTime.parse("17:00"), schedules.get(1).getEndTime());
        assertEquals(Days.WEDNESDAY, schedules.get(1).getDay());
    }


    @Test
    public void testGetSpecialistResource(){
        // Crea una simulación de PanacheQuery

        when(specialistService.findById(1l)).thenReturn(specialist);
        Response response = specialistResource.getOneSpecialist(1L);

        assertNotNull(response);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertNotNull(response.getEntity());
        SpecialistDTO entity = (SpecialistDTO) response.getEntity();

        assertNotNull(entity.getId());

        // Verifica las propiedades del especialista en la lista
        assertEquals("Noa", entity.getFirstName());
        assertEquals("Nao", entity.getLastName());
        assertEquals(Speciality.DERMATOLOGY, entity.getSpeciality());

        // Verifica las propiedades de la ubicación del especialista en la lista
        Location location = entity.getLocation();
        assertEquals("Avenida Corrientes 456", location.getStreet());
        assertEquals("Buenos Aires", location.getLocality());
        assertEquals("Ciudad Autónoma de Buenos Aires", location.getProvince());
        assertEquals("Argentina", location.getCountry());

        // Verifica las propiedades de los horarios del especialista en la lista
        List<Schedule> schedules = entity.getScheduleList();
        assertEquals(2, schedules.size()); // Verifica que haya dos horarios

        // Verifica las propiedades del primer horario del especialista en la lista
        assertEquals(LocalTime.parse("08:00"), schedules.get(0).getStartTime());
        assertEquals(LocalTime.parse("12:00"), schedules.get(0).getEndTime());
        assertEquals(Days.SUNDAY, schedules.get(0).getDay());

        // Verifica las propiedades del segundo horario del especialista en la lista
        assertEquals(LocalTime.parse("13:00"), schedules.get(1).getStartTime());
        assertEquals(LocalTime.parse("17:00"), schedules.get(1).getEndTime());
        assertEquals(Days.WEDNESDAY, schedules.get(1).getDay());
    }
/*
    @Test
    public void testCreateSpecialistResource(){
        // Configurar el comportamiento del mock del servicio
        when(specialistService.create(any(Specialist.class))).thenReturn(Response.status(Response.Status.CREATED).build());

        // Llamar al método del recurso
        Response response = specialistResource.createSpecialist(specialist);

        // Verificar el código de estado y el contenido de la respuesta
        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        //assertEquals(specialist, response.getEntity());

        // Verificar que el método create del servicio fue llamado una vez
        verify(specialistService, times(1)).create(any(Specialist.class));
    }*/
}
