package person.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import location.model.Location;
import lombok.Data;
import lombok.EqualsAndHashCode;
import schedule.model.Schedule;
import shift.entity.Shift;

import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Specialist extends User{
	@Id
	@GeneratedValue
	private Long id;
	private Speciality speciality;

	@ManyToMany
	@JoinTable(
			name = "specialist_schedule",
			joinColumns = @JoinColumn(name = "specialist_id"),
			inverseJoinColumns = @JoinColumn(name = "schedule_id")
	)
	private List<Schedule> schedules; // Horarios

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "location_id", referencedColumnName = "id")
	private Location location; // Ubicación

}
