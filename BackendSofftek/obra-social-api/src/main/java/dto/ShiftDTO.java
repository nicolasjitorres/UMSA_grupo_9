package dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.Affiliate;
import model.Specialist;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShiftDTO {
    private Long id;
    private String description; // Descripción del turno
    private LocalDate date; // Fecha del turno
    private LocalTime time; // Hora del turno
    private Boolean state; // Estado vigente
    private Specialist specialist; // ID del especialista
    private Affiliate affiliated; // ID del afiliado
}