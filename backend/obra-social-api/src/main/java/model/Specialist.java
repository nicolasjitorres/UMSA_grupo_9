package model;

import jakarta.persistence.*;
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

	@Enumerated(EnumType.STRING)
	private Speciality speciality;

	@ManyToOne
	@JoinColumn(name = "location_id")
	@JsonBackReference
	private Location location; // Ubicación


}