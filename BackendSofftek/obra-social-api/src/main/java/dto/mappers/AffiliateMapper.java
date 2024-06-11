package dto.mappers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import dto.AffiliateDTO;
import model.Affiliate;

public class AffiliateMapper {
//	Aqui se crean las funciones que mapearan los datos de dto a entidad y viceversa.
//	En el caso de entityToDto, deseamos no compartir datos sensibles como puede ser la contraseña
//	Utilizaremos un método para hashear la contraseña
	
	private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	private static String hashPassword(String password) {
		return passwordEncoder.encode(password);
	}
	
	public static Affiliate dtoToEntity(AffiliateDTO affiliateDTO) {
		Affiliate affiliate = new Affiliate();
		affiliate.setDni(affiliateDTO.getDni());
		affiliate.setEmail(affiliateDTO.getEmail());
		affiliate.setFirstName(affiliateDTO.getFirstName());
		affiliate.setLastName(affiliateDTO.getLastName());
		affiliate.setHealthInsuranceCode(affiliateDTO.getHealthInsuranceCode());
		affiliate.setId(affiliateDTO.getId());
		affiliate.setRole(affiliateDTO.getRole());
		
//		Aqui utilizamos el metodo para hashear la contraseña y asi guardarla de manera segura en la BD, para en un futuro realizar validaciones
		if (affiliateDTO.getPassword() != null) {
			String hashedPassword = hashPassword(affiliateDTO.getPassword());
			affiliate.setPassword(hashedPassword);			
		}
		
		return affiliate;
	}
	
	public static AffiliateDTO entityToDto(Affiliate affiliate) {
		AffiliateDTO affiliateDTO = new AffiliateDTO();
		affiliateDTO.setDni(affiliate.getDni());
		affiliateDTO.setEmail(affiliate.getEmail());
		affiliateDTO.setFirstName(affiliate.getFirstName());
		affiliateDTO.setLastName(affiliate.getLastName());
		affiliateDTO.setHealthInsuranceCode(affiliate.getHealthInsuranceCode());
		affiliateDTO.setId(affiliate.getId());
		affiliateDTO.setRole(affiliate.getRole());
		affiliate.setPassword(affiliate.getPassword());
		
		return affiliateDTO;
	}

}
