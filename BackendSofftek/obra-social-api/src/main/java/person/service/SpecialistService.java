package person.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import location.model.Location;
import location.repository.LocationRepository;
import person.dto.SpecialistDTO;
import person.model.Role;
import person.model.Specialist;
import person.model.Speciality;
import person.repository.SpecialistRepository;
import schedule.model.Schedule;
import schedule.repository.ScheduleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Transactional
@ApplicationScoped
public class SpecialistService {
	@Inject
	private SpecialistRepository specialistRepository;
	@Inject
	private LocationRepository locationRepository;
	@Inject
	private ScheduleRepository scheduleRepository;

	public Response listAll() {
		List<Specialist> specialists = specialistRepository.findAll().stream().toList();
		List<SpecialistDTO> specialistDTOs = new ArrayList<>();
		for (Specialist specialist : specialists) {
			SpecialistDTO specialistDTO = convertSpecialistToDTO(specialist);
			specialistDTOs.add(specialistDTO);
		}
		return Response.ok(specialistDTOs)
				.build();
	}

	public Response findById(Long id) {
		return Response.ok(convertSpecialistToDTO(specialistRepository.findById(id))).build();
	}

	public Response create(SpecialistDTO newSpecialistDTO) {
		if (newSpecialistDTO.getSpeciality() == null) {
			return Response.status(400)
					.entity("El campo especialidad es obligatorio.")
					.build();
		}else {
			Specialist newSpecialist = this.DTOtoSpecialist(newSpecialistDTO);
			for (Schedule schedule : newSpecialist.getSchedules()) {
				schedule.setSpecialist(newSpecialist);
			}
			//este bloque lo que permite es que si existe la ubicacion se la agrega al especialista
			if (newSpecialist.getLocation() != null) {
				Location location = newSpecialist.getLocation();
				Optional<Location> existingLocation = locationRepository.findByDetails(
						location.getStreet(),
						location.getLocality(),
						location.getProvince(),
						location.getCountry()
				);
				if (existingLocation.isPresent()) {
					//si existe lo agrega al especialista
					newSpecialist.setLocation(existingLocation.get());
				} else {
					//sino lo persiste
					locationRepository.persist(location);
				}
			}
			//y luego persiste el especialista
			specialistRepository.persist(newSpecialist);
			return Response.status(Response.Status.CREATED)
					.entity(convertSpecialistToDTO(newSpecialist))
					.build();
		}
	}

	public Response edit(Long id,SpecialistDTO editedSpecialist) {
		Specialist specialist = specialistRepository.findById(id);
		if (specialist == null) {
			return Response.status(400)
					.entity("No existe el especialista.")
					.build();
		} else {
			if (editedSpecialist.getFirstName() != null) {
				specialist.setFirstName(editedSpecialist.getFirstName());
			}
			if (editedSpecialist.getLastName() != null) {
				specialist.setLastName(editedSpecialist.getLastName());
			}
			if (editedSpecialist.getDni() != null) {
				specialist.setDni(editedSpecialist.getDni());
			}
			if (editedSpecialist.getSpeciality() != null) {
				specialist.setSpeciality(Speciality.valueOf(editedSpecialist.getSpeciality()));
			}
			// Actualizar ubicación
			if (editedSpecialist.getLocation() != null) {
				Location updatedLocation = editedSpecialist.getLocation();
				locationRepository.persist(updatedLocation);
			}
			// Actualizar horarios
			if (editedSpecialist.getScheduleList() != null) {
				for (Schedule updatedSchedule : editedSpecialist.getScheduleList()) {
					scheduleRepository.persist(updatedSchedule);
				}
			}
			return Response.ok(convertSpecialistToDTO(specialistRepository.findById(id)))
					.build();
		}
	}

	public Response delete(Long id) {
		if (specialistRepository.deleteById(id)) {
			return Response.ok("Se eliminó el especialista correctamente.")
					.build();
		} else {
			return Response.status(400)
					.entity("No se eliminó debido a que no se encontró el especialista con id: "+id)
					.build();
		}
	}

	private SpecialistDTO convertSpecialistToDTO(Specialist specialist) {
		SpecialistDTO dto = new SpecialistDTO();
		dto.setId(specialist.getId().toString());
		dto.setFirstName(specialist.getFirstName());
		dto.setLastName(specialist.getLastName());
		dto.setSpeciality(specialist.getSpeciality().toString());
		dto.setScheduleList(specialist.getSchedules());
		dto.setLocation(specialist.getLocation());
		return dto;
	}

	public Specialist DTOtoSpecialist(SpecialistDTO dto) {
		if (dto == null) {
			return null;
		}
		Specialist specialist = new Specialist();
		specialist.setId(Long.getLong(dto.getId()));
		specialist.setFirstName(dto.getFirstName());
		specialist.setLastName(dto.getLastName());
		specialist.setRole(Role.USER);
		specialist.setSpeciality(Speciality.valueOf(dto.getSpeciality()));

		// Convertir y asignar schedules
		if (dto.getScheduleList() != null) {
			specialist.setSchedules(dto.getScheduleList());
		}

		// Convertir y asignar location
		if (dto.getLocation() != null) {
			specialist.setLocation(dto.getLocation());
		}

		return specialist;
	}
}
