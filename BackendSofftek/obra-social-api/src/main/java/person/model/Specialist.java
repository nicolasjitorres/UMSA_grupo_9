package person.model;

import jakarta.persistence.*;
import location.model.Location;
import lombok.Data;
import lombok.EqualsAndHashCode;
import schedule.model.Schedule;

import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Specialist extends User{
	@Id
	@GeneratedValue
	private Long id;

	@Enumerated(EnumType.STRING)
	private Speciality speciality;

	@OneToMany(mappedBy = "specialist", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<Schedule> schedules; // Horarios

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "location_id", referencedColumnName = "id")
	private Location location; // Ubicación

}
