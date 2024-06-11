package model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;

@Data //genera los metodos necesarios
@Entity //marcar la calse como entidad y asi generar la tabla de la bd
@AllArgsConstructor
@NoArgsConstructor()
public class Prescription{
	
	@Id
	@GeneratedValue
    private Long id;
    private String description;

    @OneToOne(optional = false)
    @JoinColumn(name = "shift_id", nullable = false)
    private Shift idShift;

    @Override//puse esto o genera overflow cuando se muestre
    public String toString() {
        return "Prescription{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
