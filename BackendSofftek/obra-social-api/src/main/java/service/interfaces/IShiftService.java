package service.interfaces;

import dto.ShiftDTO;
import model.Shift;

import java.util.List;

public interface IShiftService {
    public List<Shift> findShifts();
    public Shift findShiftById(Long id) throws Exception;
    public Shift addShift(ShiftDTO shiftDTO) throws Exception;
    public Shift deleteShift(Long id) throws Exception;
    public Shift editShift(Long id, ShiftDTO shiftDTO) throws Exception;
}