package model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name = "affiliate")
@EqualsAndHashCode(callSuper = false)
public class Affiliate extends User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = ": El campo 'codigo de obra social' no debe estar vacío.")
	@Column(name="health_insurance_code")
	private String healthInsuranceCode;

}