package repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import model.Schedule;

@ApplicationScoped
public class ScheduleRepository implements PanacheRepository<Schedule> {
}
