package model;

import jakarta.persistence.*;
import lombok.*;

@Data //genera los metodos necesarios
@Entity //marcar la calse como entidad y asi generar la tabla de la bd
@AllArgsConstructor
@NoArgsConstructor()
@Table(name="prescriptions")
public class Prescription{
	
	@Id
	@GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "description")
    private String description;

    @OneToOne(optional = false)
    @JoinColumn(name = "shift_id", nullable = false)
    private Shift shift;

}
