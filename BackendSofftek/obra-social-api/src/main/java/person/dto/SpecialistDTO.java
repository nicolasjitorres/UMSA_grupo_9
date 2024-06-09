package person.dto;

import location.model.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import person.model.Speciality;
import schedule.model.Schedule;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecialistDTO
{
    private String firstName;
    private String lastName;
    private String speciality;
    private List<Schedule> scheduleList;
    private Location location;
}