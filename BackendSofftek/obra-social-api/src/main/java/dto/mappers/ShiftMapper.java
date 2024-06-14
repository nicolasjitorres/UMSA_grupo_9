package dto.mappers;

import dto.ShiftDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import model.Affiliate;
import model.Shift;
import model.Specialist;
import service.SpecialistService;
import service.interfaces.IAffiliateService;
import service.interfaces.ISpecialistService;

@ApplicationScoped
public class ShiftMapper {



	private ISpecialistService specialistService;

	private IAffiliateService affiliateService;
	
	
	@Inject
	public ShiftMapper(ISpecialistService specialistService, IAffiliateService affiliateService) {
		this.specialistService = specialistService;
		this.affiliateService = affiliateService;
	}

	public Shift createShiftDto(ShiftDTO shiftDTO) throws Exception{
		if (shiftDTO == null) {
			return null;
		}
		
		Shift shift = new Shift();
		shift.setId(shiftDTO.getId());
		shift.setDate(shiftDTO.getDate());
		shift.setDescription(shiftDTO.getDescription());
		Specialist existingSpecialist = this.specialistService.getSpecialistById(shiftDTO.getSpecialistId());
		Affiliate existingAffiliate = affiliateService.getAffiliateById(shiftDTO.getAffiliatedId());
		if(existingAffiliate == null) throw new Exception("No existe este afiliado");
		if(existingSpecialist == null) throw new Exception("No existe este Especialista");
		shift.setAffiliate(existingAffiliate);
		shift.setSpecialist(existingSpecialist);
		shift.setTime(shiftDTO.getTime());
		
		return shift;
	}
	
	public Shift updateShiftDto(ShiftDTO shiftDTO) {
		if (shiftDTO == null) {
			return null;
		}
		Shift shift = new Shift();
		shift.setId(shiftDTO.getId());
		shift.setDate(shiftDTO.getDate());
		shift.setTime(shiftDTO.getTime());
		
		return shift;
	}
	
	public ShiftDTO entityToDto(Shift shift) {
		if (shift == null) {
			return null;
		}
		
		ShiftDTO shiftDto = new ShiftDTO();
		shiftDto.setId(shift.getId());
		shiftDto.setAffiliatedId(shift.getAffiliate().getId());
		shiftDto.setDate(shift.getDate());
		shiftDto.setDescription(shift.getDescription());
		shiftDto.setSpecialistId(shift.getSpecialist().getId());
		shiftDto.setTime(shift.getTime());
		
		return shiftDto;
	}

}
