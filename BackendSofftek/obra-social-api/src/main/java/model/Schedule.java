package model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import model.enums.Days;

import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "schedules")
public class Schedule extends PanacheEntity {
    @Column(name = "start_time")
    private LocalTime startTime;
    @Column(name = "end_time")
    private LocalTime endTime;
    @Column(name = "schedule_day")
    private Days day;

    @JsonIgnore //ignora al especialista en el json enviado y evita el overflow
    @ManyToOne
    @JoinColumn(name = "specialist_id")
    private Specialist specialist; // Especialista

    public Schedule() {
    }

    public Schedule(Long id, LocalTime startTime, LocalTime endTime, Days day) {
        this.id=id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.day = day;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Days getDay() {
        return day;
    }

    public void setDay(Days day) {
        this.day = day;
    }

    public Specialist getSpecialist() {
        return specialist;
    }

    public void setSpecialist(Specialist specialist) {
        this.specialist = specialist;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule that = (Schedule) o;
        return Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime) && day == that.day;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, endTime, day);
    }
}
