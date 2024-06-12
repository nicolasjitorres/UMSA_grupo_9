package service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import service.interfaces.IShiftService;
import repository.ShiftRepository;
import model.Shift;

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

	@Transactional
	public Shift editShift(Long id, Shift shift) {
		Shift existingShift = shiftRepository.findById(id);

		if (existingShift != null) {
			existingShift.setDate(shift.getDate());
			existingShift.setTime(shift.getTime());
			existingShift.setState(shift.getState());
			shiftRepository.persistAndFlush(existingShift);
			return existingShift;
		} else {
			return null;
//			throw new Exception("El afiliado con id " + id + " no existe.");
		}
	}

	@Transactional
	public Shift deleteShift(Long id) {
		Shift existingShift = shiftRepository.findById(id);
		if (existingShift != null) {
//			throw new Exception("no existe este turno en la base de datos");
			if (LocalDate.now().isAfter(existingShift.getDate())) {
//			throw new Exception("no se puede cancelar un turno que ya sucedio");
				shiftRepository.deleteById(id);
				return existingShift;
			}
		}
		return null;
	}
}
