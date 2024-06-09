package person.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import shift.entity.Shift;

import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Affiliate extends User {

	@Id
	@GeneratedValue
	private Long id;
	private String healthInsuranceCode;

}
