package shift.service;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import shift.Repository.PrescriptionRepository;
import shift.Repository.ShiftRepository;
import shift.entity.Prescription;
import shift.entity.Shift;
import shift.entity.dto.PrescriptionDTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Transactional
@ApplicationScoped //esta anotacion se usa para los service
public class PrescriptionService {
    @Inject
    PrescriptionRepository prescriptionRepository;
    @Inject
    ShiftRepository shiftRepository;

    public List<Prescription> GetAllPrescriptions(){
        return prescriptionRepository.findAll().stream().toList();
    }

    public Prescription getPrescription(Long idPrescription) throws Exception {
        Prescription existingPrescription = prescriptionRepository.findById(idPrescription);
        if(existingPrescription == null) throw new Exception("no se encontro ninguna receta con este id");
        return existingPrescription;
    }

    public void AddPrescription(PrescriptionDTO prescriptionDto) throws Exception {
        if(prescriptionDto==null) throw new Exception("No se proporciono ninguna informacion");
        if(prescriptionDto.getShiftId()==null) throw new Exception("No se proporciono ningun turno");
        Shift shift = shiftRepository.findById(prescriptionDto.getShiftId());
        if(shift == null) throw new Exception("no existe este turno");
        if(LocalDate.now().isBefore(shift.getDate())) throw new Exception("no puede darle una receta a un turno que no sucedio");
        if(LocalTime.now().isBefore(shift.getTime())) throw new Exception("no puede darle una receta a un turno que no sucedio");
        if(shift.getPrescription()!=null) throw new Exception("este turno ya tiene una receta");
        prescriptionRepository.persist(prescriptionDto.toEntity(prescriptionDto,shift));
    }

    public void UpdatePrescription(Long id, Prescription prescription) throws Exception{
        if (!id.equals(prescription.getId())) throw new Exception("los ids no coinciden");
        Prescription existingPrescription = prescriptionRepository.findById(prescription.getId());
        if ( existingPrescription == null) throw  new Exception("no existe esta receta en la base de datos");
        existingPrescription.setDescription(prescription.getDescription());;
        existingPrescription.persist();
    }

    public void DeletePrescription(Long id, Prescription prescription) throws Exception{
        if (!id.equals(prescription.getId())) throw new Exception("los ids no coinciden");
        Prescription existingPrescription = prescriptionRepository.findById(prescription.getId());
        if ( existingPrescription == null) throw  new Exception("no existe esta receta en la base de datos");
        prescriptionRepository.delete(prescription);
    }
}
