package service;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import dto.AffiliateDTO;
import dto.SpecialistDTO;
import model.Affiliate;
import model.Specialist;
import repository.AffiliateRepository;
import repository.SpecialistRepository;
import repository.ShiftRepository;
import model.Shift;
import dto.ShiftDTO;
import service.interfaces.IShiftService;

import java.time.LocalDate;
import java.util.List;

@Transactional
@ApplicationScoped //esta anotacion se usa para los service
public class ShiftService implements IShiftService {

    @Inject
    private ShiftRepository shiftRepository;

    @Inject
    private AffiliateService affiliateService;

    @Inject
    private SpecialistService specialistService;

    @Transactional
    @Override
    public List<Shift> findShifts() {
        return (List<Shift>) shiftRepository.findAll();
    }

    @Override
    public Shift findShiftById(Long id) throws Exception {
        Shift existingShift = shiftRepository.findById(id);
        if(existingShift == null) throw new Exception("no se encontro ningun turno con este id");
        return existingShift;
    }

    @Transactional
    @Override
    public Shift addShift(ShiftDTO shiftDTO) throws Exception {
        if(shiftDTO==null) throw new Exception ("No se mandaron datos");
        //tembien tengo que hacer una excepcion que busque un turno con
        //el espcialsitaID y afilaidoID y si lo encuentra tira "ya hay uan receta para este especialsita y afiliado"
        Shift newShift = new Shift(); // aca tendria que ser shiftDTO.toEntity()
        shiftRepository.persist(newShift);
        return newShift;
    }

    @Transactional
    @Override
    public Shift deleteShift(Long id) throws Exception {
        Shift existingShift = shiftRepository.findById(id);
        if (existingShift == null) throw new Exception("no existe este turno en la base de datos");
        if (LocalDate.now().isAfter(existingShift.getDate())) throw new Exception("no se puede cancelar un turno que ya sucedio");
        shiftRepository.deleteById(id);
        return existingShift;
    }

    @Transactional
    @Override
    public Shift editShift(Long id, ShiftDTO shiftDTO) throws Exception {
        Shift existingShift = shiftRepository.findById(id);
        if(existingShift==null) throw new Exception ("No existe ese shift");
        if(shiftDTO==null) throw new Exception ("No se mandaron datos");
        //existingShift.setSpecialist(existSpecialist); buscar el especialsita
        existingShift.setDate(shiftDTO.getDate());
        existingShift.setTime(shiftDTO.getTime());
        existingShift.setDescription(shiftDTO.getDescription());
        shiftRepository.getEntityManager().merge(existingShift);
        return existingShift;
    }
}
