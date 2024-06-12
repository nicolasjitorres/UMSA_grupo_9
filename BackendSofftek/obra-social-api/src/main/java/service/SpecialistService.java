package service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import model.Location;
import model.Specialist;
import repository.LocationRepository;
import repository.SpecialistRepository;
import service.interfaces.ISpecialistService;
import java.util.List;


@ApplicationScoped
@Transactional
public class SpecialistService implements ISpecialistService{

	private SpecialistRepository specialistRepository;
	private LocationRepository locationRepository;
	private LocationService locationService;
	
	@Inject
	public SpecialistService(SpecialistRepository specialistRepository, LocationRepository locationRepository, LocationService locationService) {	
		this.specialistRepository = specialistRepository;
		this.locationRepository = locationRepository;
		this.locationService = locationService;
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
	public Specialist addSpecialist(Specialist newSpecialist){
		Location location = newSpecialist.getLocation();
        Location existingLocation = locationRepository.findByDetails(
            location.getStreet(),
            location.getLocality(),
            location.getProvince(),
            location.getCountry()
        );

        if (existingLocation != null) {
            newSpecialist.setLocation(existingLocation);
        } else {
        	locationService.addLocation(location);
//            locationRepository.persist(location);
        }
		specialistRepository.persist(newSpecialist);
		return newSpecialist;
	}

	@Override
	public Specialist editSpecialist(Long id, Specialist editedSpecialist){
		Specialist existingSpecialist = specialistRepository.findById(id);
		
		if (existingSpecialist != null) {
			existingSpecialist.setFirstName(editedSpecialist.getFirstName());
			existingSpecialist.setLastName(editedSpecialist.getLastName());
			existingSpecialist.setDni(editedSpecialist.getDni());
			specialistRepository.persistAndFlush(existingSpecialist);
			return existingSpecialist;
		} else {
			return null;
//			throw new Exception("El afiliado con id " + id + " no existe.");
		}
		
	}

	@Override
	public Specialist deleteSpecialist(Long id){
		Specialist existingSpecialist = specialistRepository.findById(id);
		if (existingSpecialist != null) {
			specialistRepository.deleteById(id);
			return existingSpecialist;
		} else {
			return null;
//			throw new Exception("El afiliado con id " + id + " no existe.");
		}
	}
	
}
