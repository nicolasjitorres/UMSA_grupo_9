package security;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserCredentials {
    @NotBlank(message = ": El campo 'email' no debe estar vacío.")
	@Email(message = ": El campo 'email' debe contener un email válido.")
	private String email;

	@NotBlank(message = ": El campo 'contraseña' no debe estar vacío.")
    @Size(min = 8, message = ": El campo 'contraseña' debe tener al menos 8 caracteres.")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = ": El campo 'contraseña' debe contener al menos una mayúscula, una minúscula, un número y un carácter especial.")
	private String password;
}
