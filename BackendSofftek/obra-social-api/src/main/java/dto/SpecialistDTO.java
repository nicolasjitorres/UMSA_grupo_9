package dto;

import model.Location;
import model.enums.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.Schedule;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecialistDTO{
    private Long id;
    
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
    
    @NotNull(message = ": El campo 'especialidad' no debe estar vacío.")
    private Speciality speciality;
    
    @NotEmpty(message = ": El especialista debe tener al menos un horario.")
    private List<Schedule> schedules;
    
    @NotNull(message = ": El especialista debe tener al menos una ubicación.")
    private Location location;
    private Role role;
    
    @NotBlank(message = ": El campo 'email' no debe estar vacío.")
    @Email(message = ": El campo 'email' debe contener un email válido.")
	private String email;
    
    @NotBlank(message = ": El campo 'contraseña' no debe estar vacío.")
    @Size(min = 8, message = ": El campo 'contraseña' debe tener al menos 8 caracteres.")
    //@Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = ": El campo 'contraseña' debe contener al menos una mayúscula, una minúscula, un número y un carácter especial.")
	private String password;
}
