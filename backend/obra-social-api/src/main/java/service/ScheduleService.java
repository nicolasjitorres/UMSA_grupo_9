package service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import model.Schedule;
import model.Specialist;
import repository.ScheduleRepository;
import service.interfaces.IScheduleService;
import validator.ScheduleValidator;

import java.util.List;

@ApplicationScoped
@Transactional
public class ScheduleService implements IScheduleService {

	@Inject
	private ScheduleRepository scheduleRepository;
	@Inject
	private ScheduleValidator scheduleValidator;

	@Inject
	private SpecialistService specialistService;

	@Override
	public List<Schedule> findSchedules() {
		return scheduleRepository.findAll().stream().toList();
	}

	@Override
	public Schedule findScheduleById(Long id) {
		return scheduleRepository.findById(id);
	}

	@Override
	public Schedule addSchedule(Long idSpecialist, Schedule schedule) throws Exception {
		List<String> scheduleErrors = scheduleValidator.validateSchedule(schedule);
		if (scheduleErrors != null) throw new IllegalArgumentException(scheduleErrors.toString());

		Specialist existingSpecialist  = specialistService.getSpecialistById(idSpecialist);
		if(existingSpecialist==null) throw new IllegalArgumentException("No existe el especialista con la ID: "+idSpecialist);
		schedule.setSpecialist(existingSpecialist);
		scheduleRepository.persist(schedule);
		return schedule;
	}

	@Override
	public Schedule deleteSchedule(Long id) throws Exception {
		Schedule deletedSchedule = scheduleRepository.findById(id);
		if (deletedSchedule == null)
			throw new Exception(("No existe horario con la id: " + id));
		scheduleRepository.deleteById(id);
		return deletedSchedule;
	}

	@Override
	public Schedule editSchedule(Long id, Schedule schedule) throws Exception {
		Schedule existingSchedule = scheduleRepository.findById(id);
		List<String> scheduleErrors = scheduleValidator.validateSchedule(schedule);
		if (existingSchedule == null)
			throw new Exception(("No existe horario con la id: " + id));
		if (scheduleErrors != null)
			throw new IllegalArgumentException(scheduleErrors.toString());

		existingSchedule.setDayOfWeek(schedule.getDayOfWeek());
		existingSchedule.setSpecialist(schedule.getSpecialist());
		existingSchedule.setStartTime(schedule.getStartTime());
		existingSchedule.setEndTime(schedule.getEndTime());

		scheduleRepository.getEntityManager().merge(existingSchedule);
		return scheduleRepository.findById((id));

	}

	@Override
	public List<Schedule> findScheduleByIDSpecialist(Long idSpecialist) {
		return scheduleRepository.findSchedulesBySpecialistId(idSpecialist);
	}

	@Override
	public void deleteSchedulesByIDSpecialist(Long idSpecialist) throws Exception{
		List<Schedule> schedules = scheduleRepository.findSchedulesBySpecialistId(idSpecialist);
		for (Schedule schedule : schedules)
		{
			scheduleRepository.deleteById(schedule.getId());
		}
	}


}