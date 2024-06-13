package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.enums.Day;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "schedules")
public class Schedule{

	@Id
	@GeneratedValue
	private Long id;
    @Column(name = "start_time")
    private LocalTime startTime;
    @Column(name = "end_time")
    private LocalTime endTime;
	@Enumerated
    private Day dayOfWeek;

    @ManyToOne
    @JoinColumn(name = "specialist_id")
    @JsonBackReference
    private Specialist specialist;

}
