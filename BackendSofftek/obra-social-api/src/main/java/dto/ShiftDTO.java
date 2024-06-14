package dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShiftDTO {
    private Long id;
    private String description; // Descripción del turno
    private LocalDate date; // Fecha del turno
    private LocalTime time; // Hora del turno
    private Long specialistId; // ID del especialista
    private Long affiliatedId; // ID del afiliado
}