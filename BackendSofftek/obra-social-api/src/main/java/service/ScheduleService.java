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
	public List<Schedule> getAllSchedules() {
		return scheduleRepository.findAll().stream().toList();
	}

	@Override
	public Schedule getScheduleById(Long id) {
		return scheduleRepository.findById(id);
	}

	@Override
	public Schedule addSchedule(Schedule schedule) {
		scheduleRepository.persist(schedule);
		return schedule;
	}

	public Schedule editSchedule(Long id, Schedule schedule) {
		Schedule existingSchedule = scheduleRepository.findById(id);
		if (existingSchedule != null) {
			existingSchedule.setDayOfWeek(schedule.getDayOfWeek());
			existingSchedule.setStartTime(schedule.getStartTime());
			existingSchedule.setEndTime(schedule.getEndTime());
			scheduleRepository.persistAndFlush(existingSchedule);
			return scheduleRepository.findById((id));
		} else {
			return null;
//			throw new Exception(("No existe horario con la id: " + id));
		}
	}

	@Override
	public Schedule deleteSchedule(Long id) {
		Schedule existingSchedule = scheduleRepository.findById(id);
		if (existingSchedule != null) {
			scheduleRepository.deleteById(id);
			return existingSchedule;
		} else {
			return null;
//			throw new Exception(("No existe horario con la id: " + id));
		}
	}
}
