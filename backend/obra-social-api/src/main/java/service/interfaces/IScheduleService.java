package service.interfaces;

import jakarta.enterprise.context.ApplicationScoped;
import model.Schedule;

import java.util.List;

@ApplicationScoped
public interface IScheduleService {
    public List<Schedule> findSchedules();
    public Schedule findScheduleById(Long id);
    public Schedule addSchedule(Long idSpecialist, Schedule schedule)throws Exception;
    public Schedule deleteSchedule(Long id)throws Exception;
    public Schedule editSchedule(Long id, Schedule schedule)throws Exception;
    public List<Schedule> findScheduleByIDSpecialist(Long idSpecialist);

    public void deleteSchedulesByIDSpecialist(Long idSpecialist)throws Exception;

}