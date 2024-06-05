package shift.dao;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import shift.entity.Shift;

import java.time.LocalDate;
import java.time.LocalTime;


@ApplicationScoped
public class ShiftRepository implements PanacheRepository<Shift>{

    public Shift findByDateAndHour(LocalDate date, LocalTime hour) {
        return find("date = ?1 and hour = ?2", date, hour).firstResult();
    }
}
