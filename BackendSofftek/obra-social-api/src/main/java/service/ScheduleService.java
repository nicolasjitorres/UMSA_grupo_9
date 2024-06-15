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
	public Schedule addSchedule(Schedule schedule) throws Exception{
		if(schedule.getDayOfWeek()!=null) {
			scheduleRepository.persist(schedule);
			return schedule;
		}
		else
		{
			throw new Exception(("Faltan agregar campos para el horario"));
		}
	}
	
	@Override
	public Schedule deleteSchedule(Long id) throws Exception{
		Schedule deletedSchedule = scheduleRepository.findById(id);
		if(deletedSchedule!=null)
		{
			scheduleRepository.deleteById(id);
			return deletedSchedule;
		}
		else {
			throw new Exception(("No existe horario con la id: "+id));
		}
	}
	
	@Override
	public Schedule editSchedule(Long id, Schedule schedule)throws Exception {
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
			scheduleRepository.getEntityManager().merge(existingSchedule);
			return scheduleRepository.findById((id));
		}
		else {
			throw new Exception(("No existe horario con la id: "+id));
		}
	}

}