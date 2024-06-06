package person.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Affiliate extends User {

	@Id
	@GeneratedValue
	private Long id;
	
	//private String speciality;
	
	
	
}
