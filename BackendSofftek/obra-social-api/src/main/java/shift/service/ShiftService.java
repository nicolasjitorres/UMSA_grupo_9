package shift.service;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import shift.Repository.ShiftRepository;
import shift.entity.Shift;

import java.time.LocalDate;
import java.util.List;

@Transactional
@ApplicationScoped //esta anotacion se usa para los service
public class ShiftService {

    @Inject
    ShiftRepository shiftRepository;

    public List<Shift> GetAllShift(){
        return shiftRepository.findAll().stream().toList();
    }

    public Shift GetShift(Long idShift) throws Exception {
        Shift existingShift = shiftRepository.findById(idShift);
        if(existingShift == null) throw new Exception("no se encontro ningun turno con este id");
        return existingShift;
    }

    public void AddShift(Shift shift) throws Exception {
        if(shift==null) throw new Exception("No se proporciono ninguna informacion");
        if(shiftRepository.findByDateAndHour(shift.getDate(),shift.getTime()) != null) throw  new Exception("ya existe este Turno");
        if(shift.getDate().isBefore(LocalDate.now())) throw  new Exception("la fecha del turno debe ser superior a la de hoy");
        shiftRepository.persist(shift);
    }

    public void UpdateShift(Long id,Shift shift) throws Exception {
        if(shift==null) throw new Exception("No se proporciono ninguna informacion");
        if (!id.equals(shift.getId())) throw new Exception("los ids no coinciden");
        Shift existingShift = shiftRepository.findById(shift.getId());
        if ( existingShift == null) throw  new Exception("no existe este turno en la base de datos");
        existingShift.setDescription(shift.getDescription());;
        existingShift.setDate(shift.getDate());
        existingShift.setTime(shift.getTime());
        existingShift.setState(shift.getState());
        existingShift.persist();
    }

    public void DeleteShift(Long id, Shift shift) throws Exception {
        if(shift==null) throw new Exception("No se proporciono ninguna informacion");
        if (!id.equals(shift.getId())) throw new Exception("los ids no coinciden");
        Shift existingShift = shiftRepository.findById(shift.getId());
        if ( existingShift == null) throw  new Exception("no existe este turno en la base de datos");
        if(existingShift.getDate().isAfter(LocalDate.now())) throw new Exception("no se puede cancelar un turno que ya sucedio");
        existingShift.setState(!shift.getState());
        existingShift.persist();
    }
}