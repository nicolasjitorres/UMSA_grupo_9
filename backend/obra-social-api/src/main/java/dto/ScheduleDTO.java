package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.enums.Day;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDTO{
    private Long id;
    private String startTime;
    private String endTime;
    private Day dayOfWeek;
    private Long specialistId;
}
