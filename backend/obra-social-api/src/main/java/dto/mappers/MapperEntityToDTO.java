package dto.mappers;

import dto.ScheduleDTO;
import model.Schedule;

public class MapperEntityToDTO
{
    public static ScheduleDTO entityToDTO(Schedule schedule)
    {
        ScheduleDTO dto = new ScheduleDTO(schedule.getId(), schedule.getStartTime(), schedule.getEndTime(), schedule.getDayOfWeek(), schedule.getSpecialist().getId());
        return dto;
    }

}
