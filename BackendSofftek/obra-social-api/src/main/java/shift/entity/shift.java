package shift.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;


@Data
//@Entity //descomentar cuando se conecte a la bd
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class shift extends PanacheEntity {
    private Long idPatient;
    private Long idEspecialist;
    private String description;
    private LocalDate date;
    private Boolean state;
    private Long idTimeTable ;
}
