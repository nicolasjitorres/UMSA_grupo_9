package service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import model.Schedule;
import repository.ScheduleRepository;
import service.interfaces.IScheduleService;

import java.util.List;

@ApplicationScoped
@Transactional
public class ScheduleService implements IScheduleService {
	@Inject
	private ScheduleRepository scheduleRepository;

	@Override
	public List<Schedule> findSchedules() {
		return scheduleRepository.findAll().stream().toList();
	}

	@Override
	public Schedule findScheduleById(Long id) {
		return scheduleRepository.findById(id);
	}

	@Override
	public Schedule addSchedule(Schedule schedule) {
		scheduleRepository.persist(schedule);
		return null;
	}

	@Override
	public Schedule deleteSchedule(Long id) {
		scheduleRepository.deleteById(id);
		return null;
	}

	public Schedule editSchedule(Long id, Schedule schedule) {
		Schedule existingSchedule = scheduleRepository.findById(id);
		if (existingSchedule != null) {
			if (schedule.getDayOfWeek() != null) {
				existingSchedule.setDayOfWeek(schedule.getDayOfWeek());
			}
			if (schedule.getStartTime() != null) {
				existingSchedule.setStartTime(schedule.getStartTime());
			}
			if (schedule.getEndTime() != null) {
				existingSchedule.setEndTime(schedule.getEndTime());
			}
			scheduleRepository.persist(existingSchedule);
		}
		return null;
	}

}
