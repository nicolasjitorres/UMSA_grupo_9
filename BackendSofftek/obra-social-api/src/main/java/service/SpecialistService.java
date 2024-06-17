package service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import model.Location;
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

	private SpecialistRepository specialistRepository;
	private LocationRepository locationRepository;
	private LocationService locationService;
	private SpecialistValidator specialistValidator;

	@Inject
	public SpecialistService(SpecialistRepository specialistRepository, LocationRepository locationRepository,
			LocationService locationService, SpecialistValidator specialistValidator) {
		this.specialistRepository = specialistRepository;
		this.locationRepository = locationRepository;
		this.locationService = locationService;
		this.specialistValidator = specialistValidator;
	}

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

		Location location = newSpecialist.getLocation();
		Location existingLocation = locationRepository.findByDetails(location.getStreet(), location.getLocality(),
				location.getProvince(), location.getCountry());

		if (existingLocation != null) {
			newSpecialist.setLocation(existingLocation);
		} else {
			try {
				locationService.addLocation(location);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
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
		if (existingSpecialist != null) {
			specialistRepository.deleteById(id);
			return existingSpecialist;
		} else {
			throw new Exception("El especialista con id " + id + " no existe.");
		}
	}

}
