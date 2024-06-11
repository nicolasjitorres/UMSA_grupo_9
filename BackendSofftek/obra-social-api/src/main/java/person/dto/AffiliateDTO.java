package person.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import person.model.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AffiliateDTO{
    private Long id;
    private String firstName;
    private String lastName;
    private String healthInsuranceCode;
    private String dni;
    private Role role;
	private String email;
	private String password;
}
