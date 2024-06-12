package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionDTO {
    private Long id;
    private String description;
    private Long idShift;
}
