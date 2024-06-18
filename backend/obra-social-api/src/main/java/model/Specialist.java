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
@Table(name="specialists")
public class Specialist extends User{
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "speciality")
	private Speciality speciality;

	@OneToMany(mappedBy = "specialist", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<Schedule> schedules; // Horarios

	@ManyToOne
	@JoinColumn(name = "location_id")
	@JsonBackReference
	private Location location; // Ubicación
	
	@OneToMany(mappedBy = "specialist", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JsonManagedReference("specialist-shift")
	private List<Shift> shifts;

}