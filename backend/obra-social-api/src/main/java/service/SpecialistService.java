package service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import model.Location;
import model.Specialist;
import repository.SpecialistRepository;
import service.interfaces.ISpecialistService;

import java.util.List;

import validator.Validator;

@ApplicationScoped
@Transactional
public class SpecialistService implements ISpecialistService {
	@Inject
	private SpecialistRepository specialistRepository;
	@Inject
	private Validator validator;
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
		List<String> existInvalidData = validator
				.validateSpecialist(newSpecialist);
		if (existInvalidData != null)
			throw new IllegalArgumentException(existInvalidData.toString());

		Location existingLocation = locationService.findLocationByDetails(newSpecialist.getLocation());

		if (existingLocation != null) {
			newSpecialist.setLocation(existingLocation);
		}

		specialistRepository.persist(newSpecialist);
		return newSpecialist;
	}

	@Override
	public Specialist editSpecialist(Long id, Specialist editedSpecialist) throws Exception {
		List<String> existInvalidData = validator
				.validateSpecialist(editedSpecialist);
		if (existInvalidData != null)
			throw new IllegalArgumentException(existInvalidData.toString());

		Specialist existingSpecialist = specialistRepository.findById(id);

		if (existingSpecialist != null) {
			existingSpecialist.setFirstName(editedSpecialist.getFirstName());
			existingSpecialist.setLastName(editedSpecialist.getLastName());
			existingSpecialist.setSpeciality(editedSpecialist.getSpeciality());
			existingSpecialist.setDni(editedSpecialist.getDni());
			existingSpecialist.setEmail(editedSpecialist.getEmail());
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
