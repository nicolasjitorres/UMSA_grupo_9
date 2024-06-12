package service.interfaces;

import model.Shift;

import java.util.List;

public interface IShiftService {
    public List<Shift> getAllShifts();
    public Shift getShiftById(Long id);
    public Shift addShift(Shift shift);
    public Shift deleteShift(Long id);
    public Shift editShift(Long id, Shift shift);
}