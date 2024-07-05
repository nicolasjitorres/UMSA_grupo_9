package mappers;

import dto.ShiftDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import model.Affiliate;
import model.Shift;
import model.Specialist;
import service.interfaces.IAffiliateService;
import service.interfaces.ISpecialistService;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ShiftMapper {

	@Inject
	private ISpecialistService iSpecialistService;

	@Inject
	private IAffiliateService iAffiliateService;


	public Shift createShiftDto(ShiftDTO shiftDTO) throws Exception{
		if (shiftDTO == null) {
			return null;
		}
		
		Shift shift = new Shift();
		shift.setId(shiftDTO.getId());
		shift.setDate(shiftDTO.getDate());
		shift.setDescription(shiftDTO.getDescription());
		Specialist existingSpecialist = iSpecialistService.getSpecialistById(shiftDTO.getSpecialistId());
		Affiliate existingAffiliate = iAffiliateService.getAffiliateById(shiftDTO.getAffiliatedId());
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
		shift.setDescription(shiftDTO.getDescription());
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

	public List<ShiftDTO> listShiftToDTO(List<Shift> shifts){
		List<ShiftDTO> listShiftDTO = new ArrayList<>();
		for(Shift shift : shifts){
			listShiftDTO.add(this.entityToDto(shift));
		}
		return listShiftDTO;
	}

}
