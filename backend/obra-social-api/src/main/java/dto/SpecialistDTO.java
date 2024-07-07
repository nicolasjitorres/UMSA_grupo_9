package dto;

import model.Location;
import model.enums.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecialistDTO{

    private Long id;
    private String firstName;
    private String lastName;
    private String dni;
    private Speciality speciality;
    private Location location;
    private Role role;
    
	private String email;

	@Size(min = 8, message = ": El campo 'contraseña' debe tener al menos 8 caracteres.")
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?+&])[A-Za-z\\d@$!%*?+&]{8,}$", message = ": El campo 'contraseña' debe contener al menos una mayúscula, una minúscula, un número y un carácter especial.")
	private String password;

}
