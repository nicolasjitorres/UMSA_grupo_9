package repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import model.Shift;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@ApplicationScoped
public class ShiftRepository implements PanacheRepository<Shift>{

    public List<Shift> findShiftActive() {
        return find("state = ?1", true).stream().toList();
    }

    public Shift findByDateAndHour(LocalDate date, LocalTime time) {
        return find("date = ?1 and time = ?2", date, time).firstResult();
    }
}
