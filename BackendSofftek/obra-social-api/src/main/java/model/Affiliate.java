package model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Affiliate extends User {

	@Id
	@GeneratedValue
	private Long id;
	private String healthInsuranceCode;
	
	@OneToMany(mappedBy = "affiliate", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<Shift> shifts;

}