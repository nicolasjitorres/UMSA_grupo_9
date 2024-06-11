package person.dto;

import location.model.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import person.model.Role;
import schedule.model.Schedule;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecialistDTO
{
    private Long id;
    private String firstName;
    private String lastName;
    private String dni;
    private String speciality;
    private List<Schedule> schedules;
    private Location location;
    private Role role;
	private String email;
	private String password;
}
