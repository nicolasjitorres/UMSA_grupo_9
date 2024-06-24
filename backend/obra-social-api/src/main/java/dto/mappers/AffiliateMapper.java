package dto.mappers;

public class AffiliateMapper {/*
//	Aqui se crean las funciones que mapearan los datos de dto a entidad y viceversa.
//	En el caso de entityToDto, deseamos no compartir datos sensibles como puede ser la contraseña
//	Utilizaremos un método para hashear la contraseña

	private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	private static String hashPassword(String password) {
		return passwordEncoder.encode(password);
	}

	public static Affiliate createAffiliateDto(AffiliateDTO affiliateDTO) {	
		if (affiliateDTO == null) {
			return null;
		}
		
		Affiliate affiliate = new Affiliate();
		affiliate.setDni(affiliateDTO.getDni());
		affiliate.setEmail(affiliateDTO.getEmail());
		affiliate.setFirstName(affiliateDTO.getFirstName());
		affiliate.setLastName(affiliateDTO.getLastName());
		affiliate.setHealthInsuranceCode(affiliateDTO.getHealthInsuranceCode());
		affiliate.setId(affiliateDTO.getId());
		affiliate.setRole(Role.USER);
		//String hashedPassword = hashPassword(affiliateDTO.getPassword());
		//affiliate.setPassword(hashedPassword);
		return affiliate;
	}
	
	public static Affiliate updateAffiliateDto(AffiliateDTO affiliateDTO) {
		if (affiliateDTO == null) {
			return null;
		}
		
		Affiliate affiliate = new Affiliate();
		affiliate.setDni(affiliateDTO.getDni());
		affiliate.setFirstName(affiliateDTO.getFirstName());
		affiliate.setLastName(affiliateDTO.getLastName());
		affiliate.setHealthInsuranceCode(affiliateDTO.getHealthInsuranceCode());
		affiliate.setId(affiliateDTO.getId());
		return affiliate;
	}

	public static AffiliateDTO entityToDto(Affiliate affiliate) {
		if (affiliate == null) {
			return null;
		}
		
		AffiliateDTO affiliateDTO = new AffiliateDTO();
		affiliateDTO.setDni(affiliate.getDni());
		affiliateDTO.setEmail(affiliate.getEmail());
		affiliateDTO.setFirstName(affiliate.getFirstName());
		affiliateDTO.setLastName(affiliate.getLastName());
		affiliateDTO.setHealthInsuranceCode(affiliate.getHealthInsuranceCode());
		affiliateDTO.setId(affiliate.getId());
		affiliateDTO.setRole(affiliate.getRole());
		//affiliateDTO.setPassword(affiliate.getPassword());

		return affiliateDTO;
	}
*/
}
