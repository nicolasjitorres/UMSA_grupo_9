package dto;

import model.Location;
import model.enums.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.Schedule;

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
