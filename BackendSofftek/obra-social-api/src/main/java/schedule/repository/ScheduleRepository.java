package schedule.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import schedule.model.Schedule;

@ApplicationScoped
public class ScheduleRepository implements PanacheRepository<Schedule> {
}
