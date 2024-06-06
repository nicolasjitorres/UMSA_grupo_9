package shift.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;


@Data //genera los metodos necesarios
@Entity //marcar la calse como entidad y asi generar la tabla de la bd
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Shift extends PanacheEntity {
    //falta el id_Paciente
    //falta el id_Especialista
    private Long id;
    private String description; //descripcion
    private LocalDate date; //fecha del turno
    private LocalTime time; // horario
    private Boolean state; //estado vigente

    @JsonIgnore //ignora la prescripcion en el json enviado y evita el overflow
    @OneToOne(mappedBy = "shift")
    private Prescription prescription;

    @Override//puse esto o genera overflow cuando se muestre
    public String toString() {
        return "Shift{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", state=" + state +
                '}';
    }
}
