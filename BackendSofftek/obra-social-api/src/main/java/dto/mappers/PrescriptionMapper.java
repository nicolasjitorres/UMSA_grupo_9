package dto.mappers;

import dto.PrescriptionDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import model.Prescription;
import model.Shift;
import service.interfaces.IShiftService;

@ApplicationScoped
public class PrescriptionMapper {

	@Inject
	private IShiftService shiftService;

	public Prescription dtoToEntity(PrescriptionDTO prescriptionDTO) {
		if (prescriptionDTO == null) {
			return null;
		}
		
		Prescription prescription = new Prescription();
		prescription.setId(prescriptionDTO.getId());
		prescription.setDescription(prescriptionDTO.getDescription());
		Shift shift = shiftService.getShiftById(prescriptionDTO.getIdShift());
		prescription.setShift(shift);
		
		return prescription;
	}
	
	public PrescriptionDTO entityToDto(Prescription prescription) {
		if (prescription == null) {
			return null;
		}
		
		PrescriptionDTO prescriptionDto = new PrescriptionDTO();
		prescriptionDto.setId(prescription.getId());
		prescriptionDto.setDescription(prescription.getDescription());
		prescriptionDto.setIdShift(prescription.getShift().getId());
		
		return prescriptionDto;
	}
	
	
}
