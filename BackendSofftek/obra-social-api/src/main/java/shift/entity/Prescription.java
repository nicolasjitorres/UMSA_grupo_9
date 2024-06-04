package shift.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import lombok.*;

@Data
//@Entity //descomentar cuando se conecte a la bd
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Prescription extends PanacheEntity {
    private String description;
    private Long idShift;
}
