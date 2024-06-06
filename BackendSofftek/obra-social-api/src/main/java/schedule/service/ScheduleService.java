package schedule.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import schedule.model.Schedule;
import schedule.repository.ScheduleRepository;

import java.util.List;

@ApplicationScoped
@Transactional
public class ScheduleService implements IScheduleService {
    @Inject
    private ScheduleRepository scheduleRepository;

    @Override
    public List<Schedule> getSchedules() {
        return scheduleRepository.findAll().stream().toList();
    }

    @Override
    public Schedule getScheduleById(Long id) {
        return scheduleRepository.findById(id);
    }

    @Override
    public void addSchedule(Schedule schedule) {
        scheduleRepository.persist(schedule);
    }

    @Override
    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }

    @Override
    public void editSchedule(Long id, Schedule schedule) {
        Schedule existingSchedule = scheduleRepository.findById(id);
        if (existingSchedule != null) {
            existingSchedule.setDay(schedule.getDay());
            existingSchedule.setStartTime((schedule.getStartTime()));
            existingSchedule.setEndTime((schedule.getEndTime()));
            scheduleRepository.persist(existingSchedule);
        }
    }
}
