package model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
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
public class Schedule extends PanacheEntity{

	@Column(name = "start_time")
    private LocalTime startTime;
	
	@Column(name = "end_time")
    private LocalTime endTime;
	
	@Column(name = "day_of_week")
	@Enumerated
    private Day dayOfWeek;

    @JsonIgnore //ignora al especialista en el json enviado y evita el overflow
    @ManyToOne
    @JoinColumn(name = "specialist_id")
    private Specialist specialist; // Especialista

}
