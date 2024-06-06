package person.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import person.model.Specialist;
import person.repository.SpecialistRepository;

@ApplicationScoped
public class SpecialistService {
	
	@Inject
	private SpecialistRepository specialistRepository;
		
	public Response listAll() {
		return Response.ok(specialistRepository.listAll())
				.build();
	}
	
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
	
	public Response create(Specialist newSpecialist) {
		if (newSpecialist.getSpeciality() == null) {
			return Response.status(400)
					.entity("El campo especialidad es obligatorio.")
					.build();
		} else {
			specialistRepository.persist(newSpecialist);
			return Response.ok(newSpecialist)
				.build();
		}
	}
	
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
