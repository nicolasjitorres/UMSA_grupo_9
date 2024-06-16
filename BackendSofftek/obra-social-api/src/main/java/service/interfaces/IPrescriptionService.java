package service.interfaces;

import dto.PrescriptionDTO;
import jakarta.enterprise.context.ApplicationScoped;
import model.Prescription;

import java.util.List;

@ApplicationScoped
public interface IPrescriptionService {
    public List<Prescription> getAllPrescriptions();
    public Prescription getPrescriptionById(Long id) throws Exception;
    public Prescription addPrescription(PrescriptionDTO prescriptionDTO) throws Exception;
    public Prescription deletePrescription(Long id) throws Exception;
    public Prescription editPrescription(Long id, PrescriptionDTO prescriptionDTO) throws Exception;
}