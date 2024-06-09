package person.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
public abstract class User{

	private String firstName;
	private String lastName;
	private String dni;
	@Enumerated(EnumType.STRING)
	private Role role;
	//private String email;
	//private String password;
}
