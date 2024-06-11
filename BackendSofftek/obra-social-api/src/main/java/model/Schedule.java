package model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.enums.Day;

import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Schedule{
	
	@Id
	@GeneratedValue
	private Long id;
    private LocalTime startTime;
    private LocalTime endTime;
    private Day dayOfWeek;

    @JsonIgnore //ignora al especialista en el json enviado y evita el overflow
    @ManyToOne
    @JoinColumn(name = "specialist_id")
    private Specialist specialist; // Especialista

}
