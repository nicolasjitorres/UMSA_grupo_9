package model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Affiliate extends User {

	@Id
	@GeneratedValue
	private Long id;
	@NotBlank(message = ": El campo 'codigo de obra social' no debe estar vacío.")
	private String healthInsuranceCode;

}