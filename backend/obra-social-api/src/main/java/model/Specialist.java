package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import model.enums.Speciality;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Specialist extends User{
	@Id
	@GeneratedValue
	private Long id;

	@NotNull(message = ": El campo 'especialidad' no debe estar vacío.")
	@Enumerated(EnumType.STRING)
	private Speciality speciality;

	@ManyToOne
	@JoinColumn(name = "location_id")
	@JsonBackReference
	private Location location; // Ubicación


}