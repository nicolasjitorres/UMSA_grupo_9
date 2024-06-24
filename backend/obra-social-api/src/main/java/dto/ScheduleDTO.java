package dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.enums.Day;


import com.fasterxml.jackson.annotation.JsonBackReference;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDTO{
    private Long id;

    @NotNull(message = ": El campo 'hora de inicio' es obligatorio.")
    @Pattern(regexp = "^([01]\\d|2[0-3]):([0-5]\\d):([0-5]\\d)$", message = ": El campo 'hora de inicio' debe estar en el formato HH:mm:ss y ser válido.")
    private String startTime;

    @NotNull(message = ": El campo 'hora de fin' es obligatorio.")
    @Pattern(regexp = "^([01]\\d|2[0-3]):([0-5]\\d):([0-5]\\d)$", message = ": El campo 'hora de fin' debe estar en el formato HH:mm:ss y ser válido.")
    private String endTime;

    @NotNull(message = ": El campo 'dia de la semana' es obligatorio.")
    private Day dayOfWeek;

    @NotNull(message = "Debe proporcionar un ID")
    private Long specialistId;
}
