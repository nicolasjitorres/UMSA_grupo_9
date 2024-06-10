package person.dto;

import location.model.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import schedule.model.Schedule;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AffiliateDTO
{
    private Long id;
    private String firstName;
    private String lastName;
    private String healthInsuranceCode;
    private String dni;
}
