package model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import model.enums.Role;

@MappedSuperclass
@Data
public abstract class User {
	@NotBlank(message = ": El campo 'nombre' no debe estar vacío.")
	@Size(min = 2, message = ": El campo 'nombre' debe tener al menos 2 caracteres.")
	private String firstName;

	@NotBlank(message = ": El campo 'apellido' no debe estar vacío.")
	@Size(min = 2, message = ": El campo 'apellido' debe tener al menos 2 caracteres.")
	private String lastName;

	@NotBlank(message = ": El campo 'dni' no debe estar vacío.")
	@Size(min = 7, max = 8, message = ": El campo 'dni' debe tener entre 7 y 8 dígitos.")
	@Pattern(regexp = "\\d+", message = ": El campo 'dni' debe contener solo dígitos númericos.")
	private String dni;

	@Enumerated(EnumType.STRING)
	private Role role;

	@NotBlank(message = ": El campo 'email' no debe estar vacío.")
	@Email(message = ": El campo 'email' debe contener un email válido.")
	private String email;

	@NotBlank(message = ": El campo 'contraseña' no debe estar vacío.")
	private String password;

}
