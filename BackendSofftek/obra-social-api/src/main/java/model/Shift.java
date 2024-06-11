package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;


@Data //genera los metodos necesarios
@Entity //marcar la calse como entidad y asi generar la tabla de la bd
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor()
public class Shift extends PanacheEntity {
    private Long id;
    private String description; //descripcion
    private LocalDate date; //fecha del turno
    private LocalTime time; // horario
    private Boolean state; //estado vigente

    @ManyToOne
    @JoinColumn(name = "specialist_id")
    private Specialist specialist;

    @ManyToOne
    @JoinColumn(name = "affiliated_id")
    private Affiliate affiliated;

    @JsonIgnore //ignora la prescripcion en el json enviado y evita el overflow
    @OneToOne(mappedBy = "shift")
    private Prescription prescription;

    public Shift(String description, LocalDate date, LocalTime time, Specialist specialist, Affiliate affiliated) {
        this.description = description;
        this.date = date;
        this.time = time;
        this.specialist = specialist;
        this.affiliated = affiliated;
    }

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
