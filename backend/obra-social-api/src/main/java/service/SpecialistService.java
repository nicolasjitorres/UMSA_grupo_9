package service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import model.Location;
import model.Schedule;
import model.Specialist;
import repository.LocationRepository;
import repository.SpecialistRepository;
import service.interfaces.ISpecialistService;
import validator.SpecialistValidator;

import java.util.List;

import dto.mappers.SpecialistMapper;

@ApplicationScoped
@Transactional
public class SpecialistService implements ISpecialistService {
	@Inject
	private SpecialistRepository specialistRepository;
	@Inject
	private SpecialistValidator specialistValidator;
	@Inject
	private ScheduleService scheduleService;
	@Inject
	private ShiftService shiftService;
	@Inject
	private LocationService locationService;


	@Override
	public List<Specialist> getAllSpecialists() {
		return specialistRepository.listAll();
	}

	@Override
	public Specialist getSpecialistById(Long id) {
		return specialistRepository.findById(id);
	}

	@Override

	public Specialist addSpecialist(Specialist newSpecialist) throws Exception {
		if (specialistRepository.findByDni(newSpecialist.getDni()) != null)
			throw new Exception("Ya existe un especialista con este dni: " + newSpecialist.getDni());

		List<String> existingErrors = specialistValidator
				.validateSpecialist(SpecialistMapper.entityToDto(newSpecialist));
		if (existingErrors != null)
			throw new IllegalArgumentException(existingErrors.toString());

		Location existingLocation = locationService.findLocationByDetails(newSpecialist.getLocation());

		if (existingLocation != null) {
			newSpecialist.setLocation(existingLocation);
		}

		specialistRepository.persist(newSpecialist);
		return newSpecialist;
	}

	@Override
	public Specialist editSpecialist(Long id, Specialist editedSpecialist) throws Exception {
		List<String> existingErrors = specialistValidator
				.validateSpecialist(SpecialistMapper.entityToDto(editedSpecialist));
		if (existingErrors != null)
			throw new IllegalArgumentException(existingErrors.toString());

		Specialist existingSpecialist = specialistRepository.findById(id);

		if (existingSpecialist != null) {
			existingSpecialist.setFirstName(editedSpecialist.getFirstName());
			existingSpecialist.setLastName(editedSpecialist.getLastName());
			existingSpecialist.setDni(editedSpecialist.getDni());
			specialistRepository.persistAndFlush(existingSpecialist);
			return existingSpecialist;
		} else {
			throw new Exception("El especialista con id " + id + " no existe.");
		}
	}

	@Override
	public Specialist deleteSpecialist(Long id) throws Exception {
		Specialist existingSpecialist = specialistRepository.findById(id);
		if(!shiftService.getShiftBySpecialistId(id).isEmpty())throw new Exception("No se puede borrar el especialista con ID: "+id+" debido a que está asociado a un turno.");
		if (existingSpecialist == null) throw new Exception("El especialista con id " + id + " no existe.");

		scheduleService.deleteSchedulesByIDSpecialist(id);

		Location location = existingSpecialist.getLocation();
		if (location != null) {
			location.getSpecialists().remove(existingSpecialist);
		}

		specialistRepository.deleteById(id);
		return existingSpecialist;
	}

}
