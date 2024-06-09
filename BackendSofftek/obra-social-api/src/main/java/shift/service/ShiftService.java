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
    private AffiliateService affiliateService;

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
    public void addShift(ShiftDTO shiftDTO) throws Exception
    {
        if(shiftDTO!=null)
        {
            Specialist existSpecialist= specialistService.findById(shiftDTO.getSpecialistId());
            Affiliate existAffiliate = affiliateService.getAffiliateById((shiftDTO.getAffiliatedId()));
            if(existSpecialist!= null && existAffiliate!=null)
            {
                shiftRepository.persist(new Shift(shiftDTO.getDescription(), shiftDTO.getDate(), shiftDTO.getTime(),existSpecialist, existAffiliate));
            }
            else {
                throw new Exception ("IDs de afiliado y especialista no pueden ser vacios");
            }
        }
        else {
            throw new Exception ("No se mandaron datos");
        }
    }

    @Transactional
    public void editShift(Long id,ShiftDTO shiftDTO) throws Exception {
        Shift existingShift =shiftRepository.findById(id);
        if(existingShift!=null)
        {
            if(shiftDTO!=null)
            {
                Specialist existSpecialist= specialistService.findById(shiftDTO.getSpecialistId());
                if(existSpecialist!= null)
                {
                    existingShift.setSpecialist(existSpecialist);
                    existingShift.setDate(shiftDTO.getDate());
                    existingShift.setTime(shiftDTO.getTime());
                    existingShift.setDescription(shiftDTO.getDescription());
                    shiftRepository.getEntityManager().merge(existingShift);
                }
                else {
                    throw new Exception ("No existe ese especialista");
                }
            }
            else
                {
                throw new Exception ("No se mandaron datos en body");
                }
        }else
            {
                throw new Exception ("No existe ese shift");
            }

/*
        if(shift==null) throw new Exception("No se proporciono ninguna informacion");
        //if (!id.equals(shift.getId())) throw new Exception("los ids no coinciden");
        Shift existingShift = shiftRepository.findById(shift.getId());
        if ( existingShift == null) throw  new Exception("No existe este turno en la base de datos");
        Specialist existSpecialist= specialistService.findById(shift.getSpecialistId());
        if(existSpecialist==null)throw  new Exception("No existe ese especialista con id: "+shift.getSpecialistId());

        existingShift.setDescription(shift.getDescription());;
        existingShift.setDate(shift.getDate());
        existingShift.setTime(shift.getTime());
        //FALTA VALIDAR SI ESTE PUEDE
        //VALIDAR NO EXISTA UN TURNO CON ESTE HORARIO Y FECHA CON ESTE ESPECIALISTA
        existingShift.setSpecialist(existSpecialist);

        //existingShift.persist();
        shiftRepository.getEntityManager().merge(existingShift);*/
    }

    @Transactional
    public void DeleteShift(Long id) throws Exception {
        Shift existingShift = shiftRepository.findById(id);
        if (existingShift == null) throw new Exception("no existe este turno en la base de datos");
        if (LocalDate.now().isAfter(existingShift.getDate()))
            throw new Exception("no se puede cancelar un turno que ya sucedio");
        shiftRepository.deleteById(id);
    }
}
