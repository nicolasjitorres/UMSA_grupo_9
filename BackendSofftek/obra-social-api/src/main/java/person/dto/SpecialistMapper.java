package person.dto;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import person.model.Specialist;

public class SpecialistMapper {

//	Aqui se crean las funciones que mapearan los datos de dto a entidad y viceversa.
//	En el caso de entityToDto, deseamos no compartir datos sensibles como puede ser la contraseña

//	Aqui instanciamos el encoder de contrasenas
	private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

//	Este metodo nos permite hashear la contrasena para agregar seguridad
	private static String hashPassword(String password) {
		return passwordEncoder.encode(password);
	}

	public static Specialist dtoToEntity(SpecialistDTO specialistDTO) {

		if (specialistDTO == null) {
			return null;
		}

		Specialist specialist = new Specialist();
		specialist.setDni(specialistDTO.getDni());
		specialist.setEmail(specialistDTO.getEmail());
		specialist.setFirstName(specialistDTO.getFirstName());
		specialist.setLastName(specialistDTO.getLastName());
		specialist.setId(specialistDTO.getId());
		specialist.setRole(specialistDTO.getRole());
		specialist.setLocation(specialistDTO.getLocation());
		specialist.setSchedules(specialistDTO.getSchedules());

//			Aqui utilizamos el metodo para hashear la contraseña y asi guardarla de manera segura en la BD, para en un futuro realizar validaciones
		if (specialistDTO.getPassword() != null) {
			String hashedPassword = hashPassword(specialistDTO.getPassword());
			specialist.setPassword(hashedPassword);
		}

		return specialist;
	}

	public static SpecialistDTO entityToDto(Specialist specialist) {

		if (specialist == null) {
			return null;
		}

		SpecialistDTO specialistDTO = new SpecialistDTO();
		specialistDTO.setDni(specialist.getDni());
		specialistDTO.setEmail(specialist.getEmail());
		specialistDTO.setFirstName(specialist.getFirstName());
		specialistDTO.setLastName(specialist.getLastName());
		specialistDTO.setId(specialist.getId());
		specialistDTO.setRole(specialist.getRole());
		specialistDTO.setLocation(specialist.getLocation());
		specialistDTO.setSchedules(specialist.getSchedules());

//			Aqui devolvemos la contrasena en "null" por razones de seguridad y mantener la privacidad
		specialistDTO.setPassword(null);

		return specialistDTO;
	}
}
