package dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionDTO {
    private Long id;
    @NotBlank(message = "Debe proporcionar una descripción")
    private String description;
    @NotNull(message = "Debe proporcionar el id de un de una Receta")
    private Long idShift;
}
