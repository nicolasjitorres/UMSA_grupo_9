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
    private String firstName;
    private String lastName;
    private String dni;
    private Speciality speciality;
    private Location location;
    private Role role;
	private String email;

}
