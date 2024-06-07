package person.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import person.model.Specialist;
import person.repository.SpecialistRepository;
import schedule.model.Schedule;

@ApplicationScoped
public class SpecialistService {


	@Inject
	private SpecialistRepository specialistRepository;


	@Transactional
	public Response listAll() {
		return Response.ok(specialistRepository.findAll().stream().toList())
				.build();
	}

	@Transactional
	public Response listOne(Long id) {
		Specialist specialist = specialistRepository.findById(id);
		if (specialist == null) {
			return Response.status(400)
					.entity("El especialista con id " + id + " no existe.")
					.build();
		} else {
		return Response.ok(specialist)
				.build();	
		}
	}

	@Transactional
	public Response create(Specialist newSpecialist) {
		if (newSpecialist.getSpeciality() == null) {
			return Response.status(400)
					.entity("El campo especialidad es obligatorio.")
					.build();
		}else {
			for (Schedule schedule : newSpecialist.getSchedules()) {
				schedule.setSpecialist(newSpecialist);
			}
			specialistRepository.persist(newSpecialist);
			return Response.status(Response.Status.CREATED)
					.entity(newSpecialist)
					.build();
		}
	}

	@Transactional
	public Response edit(Long id,Specialist editedSpecialist) {
		Specialist specialist = specialistRepository.findById(id);
		if (specialist == null) {
			return Response.status(400)
					.entity("No existe el especialista.")
					.build();
		} else {
			specialist.setFirstName(editedSpecialist.getFirstName());
			specialist.setLastName(editedSpecialist.getLastName());
			specialist.setDni(editedSpecialist.getDni());
			specialist.setSpeciality(editedSpecialist.getSpeciality());
			return Response.ok(editedSpecialist)
					.build();
		}
	}

	@Transactional
	public Response delete(Long id) {
		if (specialistRepository.deleteById(id)) {
			return Response.ok("Se eliminó el especialista correctamente.")
					.build();
		} else {
			return Response.status(400)
					.entity("No se pudo eliminar el especialista.")
					.build();
		}
	}

}
