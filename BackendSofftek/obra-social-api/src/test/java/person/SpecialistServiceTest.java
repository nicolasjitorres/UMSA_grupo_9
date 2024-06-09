package person;


import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import location.model.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import person.model.Role;
import person.model.Specialist;
import person.model.Speciality;
import person.service.SpecialistService;
import schedule.model.Days;
import schedule.model.Schedule;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@QuarkusTest
public class SpecialistServiceTest {
    @InjectMock
    SpecialistService specialistService;

    private Specialist specialist;

    @Transactional
    @BeforeEach
    void intance() {
        // Crea un nuevo especialista con sus horarios y ubicación asociados
        specialist = new Specialist();
        specialist.setId(1L);
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
    public void testGetAllSpecialistsService(){
        List<Specialist> ltsSpecialist = List.of(specialist);
        Mockito.when(specialistService.listAll()).thenReturn(Response.ok(ltsSpecialist).build());
        Response response= specialistService.listAll();

        System.out.println(response);
        //assertFalse(entity.isEmpty());
    }
}
