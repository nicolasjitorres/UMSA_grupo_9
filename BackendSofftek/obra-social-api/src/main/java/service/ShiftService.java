package service;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import dto.SpecialistDTO;
import model.Affiliate;
import model.Specialist;
import repository.AffiliateRepository;
import repository.SpecialistRepository;
import repository.ShiftRepository;
import model.Shift;
import dto.ShiftDTO;

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

//    @Inject
//    private AffiliateService affiliateService;

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

        Specialist existSpecialist =  specialistRepository.findById(shiftDTO.getSpecialist().getId());
        Affiliate existAffiliate = affiliateRepository.findById(shiftDTO.getAffiliated().getId());

//        shiftRepository.persist(shiftDTO.toEntity(shiftDTO,existSpecialist,existAffiliate));
    }

    @Transactional
    public void addShift(ShiftDTO shiftDTO) throws Exception
    {
//        if(shiftDTO!=null)
//        {
//            Specialist existSpecialist = specialistService.DTOtoSpecialist(specialistService.findById(shiftDTO.getSpecialistId()).readEntity(SpecialistDTO.class));
////            Affiliate existAffiliate = affiliateService.convertDTOToEntity(affiliateService.getAffiliateById(shiftDTO.getAffiliatedId()));
//             
//            if(existSpecialist!= null && existAffiliate!=null)
//            {
//            	Shift newShift = new Shift(shiftDTO.getDescription(), shiftDTO.getDate(), shiftDTO.getTime(), existSpecialist, existAffiliate);
//                shiftRepository.persist(newShift);
//            }
//            else {
//                throw new Exception ("IDs de afiliado y especialista no pueden ser vacios");
//            }
//        }
//        else {
//            throw new Exception ("No se mandaron datos");
//        }
    }

    @Transactional
    public void editShift(Long id,ShiftDTO shiftDTO) throws Exception {
        Shift existingShift =shiftRepository.findById(id);
        if(existingShift!=null)
        {
            if(shiftDTO!=null)
            {
                Specialist existSpecialist= specialistService.DTOtoSpecialist(specialistService.findById(shiftDTO.getSpecialist().getId()).readEntity(SpecialistDTO.class));
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
