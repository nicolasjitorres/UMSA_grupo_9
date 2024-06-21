package dto;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShiftDTO {
    private Long id;

    @NotBlank(message = "Debe proporcionar una descripción")
    private String description; // Descripción del turno

    @NotNull(message = "Debe proporcionar una fecha")
    @Future(message = "La fecha debe superar el dia de hoy")
    private LocalDate date; // Fecha del turno

    @NotNull(message = "Debe proporcionar una hora")
    private LocalTime time; // Hora del turno

    @NotNull(message = "Debe proporcionar el id de un especialsita ")
    private Long specialistId; // ID del especialista

    @NotNull(message = "Debe proporcionar el id de un un afiliado")
    private Long affiliatedId; // ID del afiliado
}