package dto;

import model.Prescription;
import model.Shift;

public class PrescriptionDTO {
    private Long id;
    private String description;
    private Long shiftId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getShiftId() {
        return shiftId;
    }

    public void setShiftId(Long shiftId) {
        this.shiftId = shiftId;
    }

    public Prescription toEntity(PrescriptionDTO dto, Shift shift) {
        Prescription prescription = new Prescription();
        prescription.setId(dto.getId());
        prescription.setDescription(dto.getDescription());
        prescription.setShift(shift);
        return prescription;
    }

    public PrescriptionDTO toDTO(Prescription prescription) {
        PrescriptionDTO dto = new PrescriptionDTO();
        dto.setId(prescription.getId());
        dto.setDescription(prescription.getDescription());
        dto.setShiftId(prescription.getShift().getId());
        return dto;
    }
}
