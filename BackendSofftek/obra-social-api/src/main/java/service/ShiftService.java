package service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import service.interfaces.IShiftService;
import repository.ShiftRepository;
import model.Shift;
import dto.ShiftDTO;
import dto.mappers.ShiftMapper;

import java.time.LocalDate;
import java.util.List;

@Transactional
@ApplicationScoped
public class ShiftService implements IShiftService {

	@Inject
	private ShiftRepository shiftRepository;
	
	public List<Shift> getAllShifts() {
		return shiftRepository.listAll();
	}

	public Shift getShiftById(Long idShift) {
		return shiftRepository.findById(idShift);
	}

	public Shift addShift(Shift shift) {
    	shiftRepository.persist(shift);
    	return shift;
    }

//    @Transactional
//    public Shift addShift(ShiftDTO shiftDTO) {
//        if(shiftDTO!=null)
//        {
//            Specialist existSpecialist = specialistService.DTOtoSpecialist(specialistService.findById(shiftDTO.getSpecialistId()).readEntity(SpecialistDTO.class));
//            Affiliate existAffiliate = affiliateService.convertDTOToEntity(affiliateService.getAffiliateById(shiftDTO.getAffiliatedId()).readEntity(AffiliateDTO.class));
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
//    	return null;
//    }

	@Transactional
	public Shift editShift(Long id, Shift shift) {
//        Shift existingShift =shiftRepository.findById(id);
//        if(existingShift!=null)
//        {
//            if(shiftDTO!=null)
//            {
//                Specialist existSpecialist= specialistService.DTOtoSpecialist(specialistService.findSpecialistById(shiftDTO.getSpecialist().getId()).ge(SpecialistDTO.class));
//                if(existSpecialist!= null)
//                {
//                    existingShift.setSpecialist(existSpecialist);
//                    existingShift.setDate(shiftDTO.getDate());
//                    existingShift.setTime(shiftDTO.getTime());
//                    existingShift.setDescription(shiftDTO.getDescription());
//                    shiftRepository.getEntityManager().merge(existingShift);
//                }
//                else {
//                    throw new Exception ("No existe ese especialista");
//                }
//            }
//            else
//                {
//                throw new Exception ("No se mandaron datos en body");
//                }
//        }else
//            {
//                throw new Exception ("No existe ese shift");
//            }
		return null;
	}


	@Transactional
	public Shift deleteShift(Long id) {
		Shift existingShift = shiftRepository.findById(id);
		if (existingShift == null)
//			throw new Exception("no existe este turno en la base de datos");
		if (LocalDate.now().isAfter(existingShift.getDate()))
//			throw new Exception("no se puede cancelar un turno que ya sucedio");
		shiftRepository.deleteById(id);
		return null;
	}
}
