package dto.mappers;

import dto.PrescriptionDTO;
import model.Prescription;

public class PrescriptionMapper {

	public static Prescription dtoToEntity(PrescriptionDTO prescriptionDTO) {
		if (prescriptionDTO == null) {
			return null;
		}
		
		Prescription prescription = new Prescription();
		prescription.setId(prescriptionDTO.getId());
		prescription.setDescription(prescriptionDTO.getDescription());
		prescription.setIdShift(prescriptionDTO.getIdShift());
		
		return prescription;
	}
	
	public static PrescriptionDTO entityToDto(Prescription prescription) {
		if (prescription == null) {
			return null;
		}
		
		PrescriptionDTO prescriptionDto = new PrescriptionDTO();
		prescriptionDto.setId(prescription.getId());
		prescriptionDto.setDescription(prescription.getDescription());
		prescriptionDto.setIdShift(prescription.getIdShift());
		
		return prescriptionDto;
	}
	
	
}
