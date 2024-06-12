package dto.mappers;

import dto.ShiftDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import model.Affiliate;
import model.Shift;
import model.Specialist;
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

	public Shift createShiftDto(ShiftDTO shiftDTO) {
		if (shiftDTO == null) {
			return null;
		}
		
		Shift shift = new Shift();
		shift.setId(shiftDTO.getId());
		shift.setDate(shiftDTO.getDate());
		shift.setDescription(shiftDTO.getDescription());
		Specialist existingSpecialist = specialistService.getSpecialistById(shiftDTO.getSpecialistId());
		Affiliate existingAffiliate = affiliateService.getAffiliateById(shiftDTO.getAffiliatedId());
		shift.setAffiliate(existingAffiliate);
		shift.setSpecialist(existingSpecialist);
		shift.setState(shiftDTO.getState());
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
		shiftDto.setState(shift.getState());
		shiftDto.setTime(shift.getTime());
		
		return shiftDto;
	}

}
