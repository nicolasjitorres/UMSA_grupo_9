package repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import model.Schedule;

import java.util.List;

@ApplicationScoped
public class ScheduleRepository implements PanacheRepository<Schedule> {
    public List<Schedule> findSchedulesBySpecialistId(Long specialistId) {
        return find("specialist.id", specialistId).list();
    }

}
