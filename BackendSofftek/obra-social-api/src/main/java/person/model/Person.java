package person.model;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass //Esto para definir como superclase que será heredada pero que no se mapeara en la BD
@Data //Genera los getters, setters, los metodos toString(), equals(), hashCode()
@AllArgsConstructor //Genera un constructor con todos los atributos
@NoArgsConstructor //Genera el constructor por defecto
public abstract class Person {

	private String firstName;
	private String lastName;
	private String dni;
	
}
