package model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "specialist_id")
    @JsonBackReference("specialist-shift")
    private Specialist specialist;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "affiliated_id")
    @JsonBackReference
    private Affiliate affiliate;

}
