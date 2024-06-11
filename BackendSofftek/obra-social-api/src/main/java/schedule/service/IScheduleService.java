package schedule.service;

import schedule.model.Schedule;

import java.util.List;

public interface IScheduleService {
    public List<Schedule> getSchedules();
    public Schedule getScheduleById(Long id);
    public void addSchedule(Schedule schedule);
    public void deleteSchedule(Long id);
    public void editSchedule(Long id, Schedule schedule);

}
