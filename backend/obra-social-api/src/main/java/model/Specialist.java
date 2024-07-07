package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import model.enums.Speciality;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table (name="specialist")
public class Specialist extends User{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = ": El campo 'especialidad' no debe estar vacío.")
	@Enumerated(EnumType.STRING)
	private Speciality speciality;

	@ManyToOne
	@JoinColumn(name = "location_id")
	@JsonBackReference
	private Location location;


}