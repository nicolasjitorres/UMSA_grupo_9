package dto.mappers;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import dto.SpecialistDTO;
import model.Schedule;
import model.Specialist;
import model.enums.Role;

public class SpecialistMapper {

//	Aqui se crean las funciones que mapearan los datos de dto a entidad y viceversa.
//	En el caso de entityToDto, deseamos no compartir datos sensibles como puede ser la contraseña

//	Aqui instanciamos el encoder de contrasenas
	private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

//	Este metodo nos permite hashear la contrasena para agregar seguridad
	private static String hashPassword(String password) {
		return passwordEncoder.encode(password);
	}

	public static Specialist createSpecialistDto(SpecialistDTO specialistDTO) {
		if (specialistDTO == null) {
			return null;
		}

		Specialist specialist = new Specialist();
		specialist.setDni(specialistDTO.getDni());
//		specialist.setEmail(specialistDTO.getEmail());
		specialist.setFirstName(specialistDTO.getFirstName());
		specialist.setLastName(specialistDTO.getLastName());
		specialist.setId(specialistDTO.getId());
		specialist.setSpeciality(specialistDTO.getSpeciality());
		specialist.setRole(Role.USER);
		specialist.setLocation(specialistDTO.getLocation());
		//List<Schedule> schedules = specialistDTO.getSchedules();
	    //if (schedules != null) {
//	        for (Schedule schedule : schedules) {
	            //schedule.setSpecialist(specialist);
	        //}
	    //}
		//specialist.setSchedules(specialistDTO.getSchedules());
		
//		Aqui utilizamos el metodo para hashear la contraseña y asi guardarla de manera segura en la BD, para en un futuro realizar validaciones
//		if (specialistDTO.getPassword() != null) {
//			String hashedPassword = hashPassword(specialistDTO.getPassword());
//			specialist.setPassword(hashedPassword);
//		}

		
		return specialist;
	}
	
	public static Specialist updateSpecialistDto(SpecialistDTO specialistDTO) {
		if (specialistDTO == null) {
			return null;
		}
		Specialist specialist = new Specialist();
		specialist.setDni(specialistDTO.getDni());
		specialist.setFirstName(specialistDTO.getFirstName());
		specialist.setLastName(specialistDTO.getLastName());
		//specialist.setId(specialistDTO.getId());
		specialist.setLocation(specialistDTO.getLocation());
		//specialist.setSchedules(specialistDTO.getSchedules());
		return specialist;
	}

	public static SpecialistDTO entityToDto(Specialist specialist) {

		if (specialist == null) {
			return null;
		}

		SpecialistDTO specialistDTO = new SpecialistDTO();
		specialistDTO.setDni(specialist.getDni());
//		specialistDTO.setEmail(specialist.getEmail());
		specialistDTO.setFirstName(specialist.getFirstName());
		specialistDTO.setLastName(specialist.getLastName());
		//specialistDTO.setId(specialist.getId());
		specialistDTO.setRole(specialist.getRole());
		specialistDTO.setSpeciality(specialist.getSpeciality());
		specialistDTO.setLocation(specialist.getLocation());
		//specialistDTO.setSchedules(specialist.getSchedules());
//		specialistDTO.setPassword(specialist.getPassword());

		return specialistDTO;
	}
}
