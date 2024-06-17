package service;

import dto.ShiftDTO;
import dto.mappers.ShiftMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import model.Schedule;
import service.interfaces.IAffiliateService;

import service.interfaces.IShiftService;
import repository.ShiftRepository;
import model.Shift;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

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
		if(shiftRepository.findByDateAndHour(shift.getDate(),shift.getTime())!=null) throw new Exception("Ya existe un turno con esta fecha y hora");
		if(DiaCorrecto(shift)==false) throw new Exception("El especialista no trabaja en el dia seleccionado");
		HorarioCorrecto(shift);
		shiftRepository.persist(shift);
		return shift;
	}

	@Transactional
	public Shift editShift(Long id, ShiftDTO shiftDTO) throws Exception{
		Shift existingShift = shiftRepository.findById(id);
		if (existingShift == null) throw new Exception("No existe ningún turno con id: "+id+" por lo tanto no se puede editar");
		Shift updateShift = shiftMapper.updateShiftDto(shiftDTO);
		existingShift.setDate(updateShift.getDate());
		existingShift.setTime(updateShift.getTime());
		shiftRepository.persistAndFlush(existingShift);
		return existingShift;
	}

	@Transactional
	public Shift deleteShift(Long id) throws Exception{
		Shift existingShift = shiftRepository.findById(id);
		if (existingShift == null) throw new Exception("No existe un turno con id: "+id+ " en la base de datos, por lo tanto no se puede borrar");
		//if (LocalDate.now().isAfter(existingShift.getDate())) {
		//throw new Exception("no se puede cancelar un turno que ya sucedio");
		shiftRepository.deleteById(id);
		return existingShift;
	}

	public Boolean DiaCorrecto(Shift shift) throws Exception{
		List<Integer> diasQueTrabaja = new ArrayList<>();
		for(Schedule s : shift.getSpecialist().getSchedules()){
			diasQueTrabaja.add(s.getDayOfWeek().ordinal());
		}
		return diasQueTrabaja.contains(shift.getDate().getDayOfWeek().ordinal());
	}

	public void HorarioCorrecto(Shift shift) throws Exception{
		for(Schedule s : shift.getSpecialist().getSchedules()){
			if (s.getDayOfWeek().ordinal() == shift.getDate().getDayOfWeek().ordinal()){
				if(shift.getTime().isBefore(LocalTime.parse(s.getStartTime())) || shift.getTime().isAfter(LocalTime.parse(s.getEndTime().toString()))) throw new Exception("el horario no esta dentro del horario atencion del especialista");
			}
		}
	}
}

