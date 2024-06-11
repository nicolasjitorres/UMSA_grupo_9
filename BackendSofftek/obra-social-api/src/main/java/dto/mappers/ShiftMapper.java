package dto.mappers;

import dto.ShiftDTO;
import model.Shift;

public class ShiftMapper {
	
	public static Shift dtoToEntity(ShiftDTO shiftDTO) {
		if (shiftDTO == null) {
			return null;
		}
		
		Shift shift = new Shift();
		shift.setId(shiftDTO.getId());
		shift.setAffiliated(shiftDTO.getAffiliated());
		shift.setDate(shiftDTO.getDate());
		shift.setDescription(shiftDTO.getDescription());
		shift.setSpecialist(shift.getSpecialist());
		shift.setState(shiftDTO.getState());
		shift.setTime(shiftDTO.getTime());
		
		return shift;
	}

}
