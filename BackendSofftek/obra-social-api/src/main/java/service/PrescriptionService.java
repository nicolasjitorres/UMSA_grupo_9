package service;

import dto.PrescriptionDTO;
import dto.mappers.PrescriptionMapper;
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

	@Inject
	private PrescriptionMapper prescriptionMapper;

	@Override
	public List<Prescription> getAllPrescriptions() {
		return prescriptionRepository.listAll();
	}

	@Override
	public Prescription getPrescriptionById(Long id) throws Exception{
		Prescription exixtingPrescription = prescriptionRepository.findById(id);
		if(exixtingPrescription == null) throw new Exception("no existe una Receta con este ID");
		return exixtingPrescription;
	}

	@Override
	public Prescription addPrescription(PrescriptionDTO prescriptionDTO){
		Prescription newPrescription = prescriptionMapper.dtoToEntity(prescriptionDTO);
		prescriptionRepository.persist(newPrescription);
		return newPrescription;
	}
	
	@Override
	public Prescription editPrescription(Long id, PrescriptionDTO prescriptionDTO) throws Exception{
		Prescription existingPrescription = prescriptionRepository.findById(id);
		if (existingPrescription == null) throw new Exception("No hay ninguna receta con este ID");
		Prescription prescriptionForUpdate = prescriptionMapper.dtoToEntity(prescriptionDTO);
		existingPrescription.setDescription(prescriptionForUpdate.getDescription());
		prescriptionRepository.persistAndFlush(existingPrescription);
		return existingPrescription;
	}

	@Override
	public Prescription deletePrescription(Long id) throws Exception {
		Prescription existingPrescription = prescriptionRepository.findById(id);
		if (existingPrescription == null) throw new Exception("No existe ninguna receta con este id");
		prescriptionRepository.deleteById(id);
		return existingPrescription;
	}
}
