package model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Data //genera los metodos necesarios
@Entity //marcar la calse como entidad y asi generar la tabla de la bd
@AllArgsConstructor
@NoArgsConstructor()
@Table(name = "shifts")
public class Shift {
	
	@Id
	@GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "description")
    private String description; //descripcion
    @Column(name = "date")
    private LocalDate date; //fecha del turno
    @Column(name = "time")
    private LocalTime time; // horario

    @ManyToOne()
    @JoinColumn(name = "specialist_id")
    @JsonBackReference("specialist-shift")
    private Specialist specialist;

    @ManyToOne()
    @JoinColumn(name = "affiliated_id")
    @JsonBackReference
    private Affiliate affiliate;

}
