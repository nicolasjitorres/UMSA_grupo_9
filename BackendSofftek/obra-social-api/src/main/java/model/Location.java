package model;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location{

	@Id
	@GeneratedValue
	private Long id;
	
	@NotBlank(message = ": El campo 'calle' no debe estar vacío.")
    private String street;
	
	@NotBlank(message = ": El campo 'localidad' no debe estar vacío.")
    private String locality;
	
	@NotBlank(message = ": El campo 'provincia' no debe estar vacío.")
    private String province;
    
	@NotBlank(message = ": El campo 'país' no debe estar vacío.")
    private String country;
    
    @JsonIgnore
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
	private List<Specialist> specialists; // especialistas asignados a la misma direccion

}