package model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;


@Data //genera los metodos necesarios
@Entity //marcar la calse como entidad y asi generar la tabla de la bd
@AllArgsConstructor
@NoArgsConstructor()
public class Shift {
	
	@Id
	@GeneratedValue
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

}
