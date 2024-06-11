package service.interfaces;

import model.Prescription;

import java.util.List;

public interface IPrescriptionService {
    public List<Prescription> getPrescriptions();
    public Prescription getPrescriptionById(Long id);
    public Prescription addPrescription(Prescription prescription);
    public Prescription deletePrescription(Long id);
    public Prescription editPrescription(Long id, Prescription prescription);
}