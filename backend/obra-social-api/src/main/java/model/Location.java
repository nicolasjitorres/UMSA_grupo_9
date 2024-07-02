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
@Table(name = "locations")
public class Location{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = ": El campo 'calle' no debe estar vacío.")
	@Column(name="street")
    private String street;
	
	@NotBlank(message = ": El campo 'localidad' no debe estar vacío.")
	@Column(name="locality")
    private String locality;
	
	@NotBlank(message = ": El campo 'provincia' no debe estar vacío.")
	@Column(name="province")
    private String province;
    
	@NotBlank(message = ": El campo 'país' no debe estar vacío.")
	@Column(name="country")
    private String country;

    @JsonIgnore
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
	private List<Specialist> specialists;

}