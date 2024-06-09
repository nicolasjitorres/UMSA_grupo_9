package shift.service;


import com.arjuna.ats.internal.arjuna.objectstore.ShadowNoFileLockStore;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import person.model.Affiliate;
import person.model.Specialist;
import person.repository.AffiliateRepository;
import person.repository.SpecialistRepository;
import person.service.AffiliateService;
import person.service.SpecialistService;
import shift.Repository.ShiftRepository;
import shift.entity.Shift;
import shift.entity.dto.ShiftDTO;

import java.time.LocalDate;
import java.util.List;

@Transactional
@ApplicationScoped //esta anotacion se usa para los service
public class ShiftService {

    @Inject
    private ShiftRepository shiftRepository;
    @Inject
    private SpecialistRepository specialistRepository;
    @Inject
    private AffiliateRepository affiliateRepository;
    @Inject
    private SpecialistService specialistService;
    @Transactional
    public List<Shift> GetAllShift(){
        return shiftRepository.findAll().stream().toList();
    }

    @Transactional
    public Shift GetShift(Long idShift) throws Exception {
        Shift existingShift = shiftRepository.findById(idShift);
        if(existingShift == null) throw new Exception("no se encontro ningun turno con este id");
        return existingShift;
    }

    @Transactional
    public void AddShift(ShiftDTO shiftDTO) throws Exception {
        if(shiftDTO==null) throw new Exception("No se proporciono ninguna informacion");
        if(shiftRepository.findByDateAndHour(shiftDTO.getDate(),shiftDTO.getTime()) != null) throw  new Exception("ya existe este Turno");
        if(LocalDate.now().isAfter(shiftDTO.getDate())) throw  new Exception("la fecha del turno debe ser superior a la de hoy");

        Specialist existSpecialist =  specialistRepository.findById(shiftDTO.getSpecialistId());
        Affiliate existAffiliate = affiliateRepository.findById(shiftDTO.getAffiliatedId());

        shiftRepository.persist(shiftDTO.toEntity(shiftDTO,existSpecialist,existAffiliate));
    }

    @Transactional
    public void UpdateShift(Long id,ShiftDTO shiftDTO) throws Exception {
        if(shiftDTO==null) throw new Exception("No se proporciono ninguna informacion");
        if (!id.equals(shiftDTO.getId())) throw new Exception("los ids no coinciden");


        Specialist existSpecialist =  specialistRepository.findById(shiftDTO.getSpecialistId());
        Affiliate existAffiliate = affiliateRepository.findById(shiftDTO.getAffiliatedId());

        Shift existingShift = shiftDTO.toEntity(shiftDTO,existSpecialist,existAffiliate);
        ///if ( existingShift == null) throw  new Exception("no existe este turno en la base de datos");

        /*existingShift.setDescription(shiftDTO.getDescription());;
        existingShift.setDate(shiftDTO.getDate());
        existingShift.setTime(shiftDTO.getTime());
        existingShift.setState(shiftDTO.getState());
        existingShift.setSpecialist();*/
        shiftRepository.getEntityManager().merge(existingShift);
    }

    @Transactional
    public void editShift(Long id, ShiftDTO shiftDTO)
    {
        Shift shiftEdit  = shiftRepository.findById(id);
        shiftEdit.setDate((shiftDTO.getDate()));
        shiftEdit.setTime(shiftDTO.getTime());
        shiftEdit.setDescription(shiftDTO.getDescription());
        shiftEdit.setSpecialist(specialistService.findById(shiftDTO.getSpecialistId()));

        shiftRepository.getEntityManager().merge(shiftEdit);
    }

    @Transactional
    public void DeleteShift(Long id, Shift shift) throws Exception {
        if(shift==null) throw new Exception("No se proporciono ninguna informacion");
        if (!id.equals(shift.getId())) throw new Exception("los ids no coinciden");
        Shift existingShift = shiftRepository.findById(shift.getId());
        if ( existingShift == null) throw  new Exception("no existe este turno en la base de datos");
        if(LocalDate.now().isAfter(shift.getDate())) throw new Exception("no se puede cancelar un turno que ya sucedio");
        existingShift.setState(!shift.getState());
        existingShift.persist();
    }
}
