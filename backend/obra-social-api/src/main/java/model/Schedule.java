package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.enums.Day;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "schedules")
public class Schedule{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotNull(message = ": El campo 'hora de inicio' es obligatorio.")
    @Pattern(regexp = "^([01]\\d|2[0-3]):([0-5]\\d):([0-5]\\d)$", message = ": El campo 'hora de inicio' debe estar en el formato HH:mm:ss y ser válido.")
    @Column(name = "start_time")
    private String startTime;
	
	@NotNull(message = ": El campo 'hora de fin' es obligatorio.")
    @Pattern(regexp = "^([01]\\d|2[0-3]):([0-5]\\d):([0-5]\\d)$", message = ": El campo 'hora de fin' debe estar en el formato HH:mm:ss y ser válido.")
    @Column(name = "end_time")
    private String endTime;
	
	@NotNull(message = ": El campo 'dia de la semana' es obligatorio y debe ser un día de la semana")
	@Enumerated
    @Column(name="day_of_week")
    private Day dayOfWeek;

    @ManyToOne
    @JoinColumn(name = "specialist_id")
    private Specialist specialist;

}
