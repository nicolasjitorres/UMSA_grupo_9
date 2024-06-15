package service;

import dto.ShiftDTO;
import dto.mappers.ShiftMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import service.interfaces.IAffiliateService;
import service.interfaces.IShiftService;
import repository.ShiftRepository;
import model.Shift;
import service.interfaces.ISpecialistService;

import java.time.LocalDate;
import java.util.List;

@Transactional
@ApplicationScoped
public class ShiftService implements IShiftService {

	@Inject
	private ShiftRepository shiftRepository;

	@Inject
	private ShiftMapper shiftMapper;

	public List<Shift> getAllShifts() {
		return shiftRepository.listAll();
	}

	public Shift getShiftById(Long idShift) {

		return shiftRepository.findById(idShift);
	}

	public Shift addShift(ShiftDTO shiftDTO) throws Exception{
		Shift shift = shiftMapper.createShiftDto(shiftDTO);
		if(shiftRepository.findByDateAndHour(shift.getDate(),shift.getTime())!=null) throw new Exception("ya existe un turno con esta fecha y hora");
		shiftRepository.persist(shift);
		return shift;
	}

	@Transactional
	public Shift editShift(Long id, ShiftDTO shiftDTO) throws Exception{
		Shift existingShift = shiftRepository.findById(id);
		if (existingShift == null) throw new Exception("no existe ningun turno con este id");
		Shift updateShift = shiftMapper.updateShiftDto(shiftDTO);
		existingShift.setDate(updateShift.getDate());
		existingShift.setTime(updateShift.getTime());
		shiftRepository.persistAndFlush(existingShift);
		return existingShift;
	}

	@Transactional
	public Shift deleteShift(Long id) throws Exception{
		Shift existingShift = shiftRepository.findById(id);
		if (existingShift == null) throw new Exception("no existe este turno en la base de datos");
		//if (LocalDate.now().isAfter(existingShift.getDate())) {
		//throw new Exception("no se puede cancelar un turno que ya sucedio");
		shiftRepository.deleteById(id);
		return existingShift;
	}
}

