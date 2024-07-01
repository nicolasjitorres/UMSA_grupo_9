package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data //genera los metodos necesarios
@Entity //marcar la calse como entidad y asi generar la tabla de la bd
@AllArgsConstructor
@NoArgsConstructor()
public class Prescription{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Debe proporcionar una descripción")
    private String description;

    @OneToOne(optional = false)
    @JoinColumn(name = "shift_id", nullable = false)
    private Shift shift;

}
