//package person.specialistTest;
//
//import io.quarkus.test.InjectMock;
//import io.quarkus.test.junit.QuarkusTest;
//
//import model.Location;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import model.enums.Role;
//import model.Specialist;
//import model.enums.Speciality;
//import repository.SpecialistRepository;
//import model.enums.Day;
//import model.Schedule;
//
//import java.time.LocalTime;
//import java.util.Arrays;
//
//import static org.mockito.Mockito.*;
//
//@QuarkusTest
//public class SpecialistRepositoryTest {
//    @InjectMock
//    SpecialistRepository specialistRepository;
//
//    private Specialist specialist;
//
//    @BeforeEach
//    void intance() {
//        // Crea un nuevo especialista con sus horarios y ubicación asociados
//        specialist = new Specialist();
//        specialist.setId(1L);
//        specialist.setFirstName("Noa");
//        specialist.setLastName("Nao");
//        specialist.setSpeciality(Speciality.DERMATOLOGY);
//        specialist.setRole(Role.USER);
//
//        // Crea y configura la ubicación del especialista
//        Location location = new Location();
//        location.setStreet("Avenida Corrientes 456");
//        location.setLocality("Buenos Aires");
//        location.setProvince("Ciudad Autónoma de Buenos Aires");
//        location.setCountry("Argentina");
//        specialist.setLocation(location);
//
//        // Crea y configura los horarios del especialista
//        Schedule schedule1 = new Schedule();
//        schedule1.setStartTime(LocalTime.parse("08:00"));
//        schedule1.setEndTime(LocalTime.parse("12:00"));
//        schedule1.setDayOfWeek(Day.SUNDAY);
//
//        Schedule schedule2 = new Schedule();
//        schedule2.setStartTime(LocalTime.parse("13:00"));
//        schedule2.setEndTime(LocalTime.parse("17:00"));
//        schedule2.setDayOfWeek(Day.WEDNESDAY);
//
//        specialist.setSchedules(Arrays.asList(schedule1, schedule2));
//    }
//
////    @Test
////    public void testGetAllSpecialistsRepository() {
////        List<Specialist> ltsSpecialist = List.of(specialist);
////
////        // Crea una simulación de PanacheQuery
////        PanacheQuery<Specialist> panacheQuery = Mockito.mock(PanacheQuery.class);
////        Mockito.when(panacheQuery.stream()).thenReturn(ltsSpecialist.stream());
////        Mockito.when(specialistRepository.findAll()).thenReturn(panacheQuery);
////
////        List<Specialist> entity = specialistRepository.findAll().stream().toList();
////
////        assertFalse(entity.isEmpty());
////
////        // Verifica las propiedades del primer especialista en la lista
////        assertEquals("Noa", entity.get(0).getFirstName());
////        assertEquals("Nao", entity.get(0).getLastName());
////        assertEquals(Speciality.DERMATOLOGY, entity.get(0).getSpeciality());
////        assertEquals(Role.USER, entity.get(0).getRole());
////
////        // Verifica las propiedades de la ubicación del primer especialista en la lista
////        Location location = entity.get(0).getLocation();
////        assertEquals("Avenida Corrientes 456", location.getStreet());
////        assertEquals("Buenos Aires", location.getLocality());
////        assertEquals("Ciudad Autónoma de Buenos Aires", location.getProvince());
////        assertEquals("Argentina", location.getCountry());
////
////        // Verifica las propiedades de los horarios del primer especialista en la lista
////        List<Schedule> schedules = entity.get(0).getSchedules();
////        assertEquals(2, schedules.size()); // Verifica que haya dos horarios
////
////        // Verifica las propiedades del primer horario del primer especialista en la lista
////        assertEquals(LocalTime.parse("08:00"), schedules.get(0).getStartTime());
////        assertEquals(LocalTime.parse("12:00"), schedules.get(0).getEndTime());
////        assertEquals(Days.SUNDAY, schedules.get(0).getDay());
////
////        // Verifica las propiedades del segundo horario del primer especialista en la lista
////        assertEquals(LocalTime.parse("13:00"), schedules.get(1).getStartTime());
////        assertEquals(LocalTime.parse("17:00"), schedules.get(1).getEndTime());
////        assertEquals(Days.WEDNESDAY, schedules.get(1).getDay());
////
////    }
////
////    @Test
////    public void testGetSpecialistByID(){
////        PanacheQuery<Specialist> panacheQuery = Mockito.mock(PanacheQuery.class);
////        Mockito.when(panacheQuery.firstResult()).thenReturn(specialist);
////        Mockito.when(specialistRepository.findById(1l)).thenReturn(specialist);
////
////        Specialist entity = specialistRepository.findById(1L);
////        assertFalse(entity==null);
////
////        // Verifica las propiedades del primer especialista en la lista
////        assertEquals("Noa", entity.getFirstName());
////        assertEquals("Nao", entity.getLastName());
////        assertEquals(Speciality.DERMATOLOGY, entity.getSpeciality());
////        assertEquals(Role.USER, entity.getRole());
////
////        // Verifica las propiedades de la ubicación del primer especialista en la lista
////        Location location = entity.getLocation();
////        assertEquals("Avenida Corrientes 456", location.getStreet());
////        assertEquals("Buenos Aires", location.getLocality());
////        assertEquals("Ciudad Autónoma de Buenos Aires", location.getProvince());
////        assertEquals("Argentina", location.getCountry());
////
////        // Verifica las propiedades de los horarios del primer especialista en la lista
////        List<Schedule> schedules = entity.getSchedules();
////        assertEquals(2, schedules.size()); // Verifica que haya dos horarios
////
////        // Verifica las propiedades del primer horario del primer especialista en la lista
////        assertEquals(LocalTime.parse("08:00"), schedules.get(0).getStartTime());
////        assertEquals(LocalTime.parse("12:00"), schedules.get(0).getEndTime());
////        assertEquals(Days.SUNDAY, schedules.get(0).getDay());
////
////        // Verifica las propiedades del segundo horario del primer especialista en la lista
////        assertEquals(LocalTime.parse("13:00"), schedules.get(1).getStartTime());
////        assertEquals(LocalTime.parse("17:00"), schedules.get(1).getEndTime());
////        assertEquals(Days.WEDNESDAY, schedules.get(1).getDay());
////    }
//
//    @Test
//    public void testMergeSpecialist(){
//        SpecialistRepository specialistMockRepository = Mockito.mock(SpecialistRepository.class);
//        specialist.setId(1L);
//        specialistMockRepository.persist(specialist);
//        specialist.setLastName("richadns");
//        specialistMockRepository.persist(specialist);
//        verify(specialistMockRepository,times(2)).persist(specialist);
//    }
//
//    @Test
//    public void testPersistSpecialist(){
//        SpecialistRepository specialistMockRepository = Mockito.mock(SpecialistRepository.class);
//        specialistMockRepository.persist(specialist);
//        verify(specialistMockRepository).persist(specialist);
//    }
//
//    @Test
//    public void testSpecialistDelete() {
//        SpecialistRepository specialistMockRepository = Mockito.mock(SpecialistRepository.class);
//        specialist.setId(1L);
//        specialistMockRepository.delete(specialist);
//        verify(specialistMockRepository).delete(specialist);
//    }
//
//}
