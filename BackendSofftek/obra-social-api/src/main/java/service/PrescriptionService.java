package service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import repository.PrescriptionRepository;
import service.interfaces.IPrescriptionService;
import model.Prescription;

import java.util.List;

@Transactional
@ApplicationScoped
public class PrescriptionService implements IPrescriptionService {
	@Inject
	private PrescriptionRepository prescriptionRepository;

	@Override
	public List<Prescription> getAllPrescriptions() {
		return prescriptionRepository.findAll().stream().toList();
	}

	@Override
	public Prescription getPrescriptionById(Long id) {
		return prescriptionRepository.findById(id);
	}

	@Override
	public Prescription addPrescription(Prescription prescription) {
		prescriptionRepository.persist(prescription);
		return prescription;
	}
	
	@Override
	public Prescription editPrescription(Long id, Prescription prescription) {
		Prescription existingPrescription = prescriptionRepository.findById(id);
		if (existingPrescription != null) {
			existingPrescription.setDescription(prescription.getDescription());
			prescriptionRepository.persistAndFlush(existingPrescription);
			return existingPrescription;
		} else {
			return null;
		}
	}

	@Override
	public Prescription deletePrescription(Long id) {
		Prescription existingPrescription = prescriptionRepository.findById(id);
		if (existingPrescription != null) {
			prescriptionRepository.deleteById(id);
			return existingPrescription;
		} else {
			return null;
		}
	}

}
