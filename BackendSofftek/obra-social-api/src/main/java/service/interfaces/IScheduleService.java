package service.interfaces;

import model.Schedule;

import java.util.List;

public interface IScheduleService {
    public List<Schedule> findSchedules();
    public Schedule findScheduleById(Long id);
    public Schedule addSchedule(Schedule schedule);
    public Schedule deleteSchedule(Long id);
    public Schedule editSchedule(Long id, Schedule schedule);

}
