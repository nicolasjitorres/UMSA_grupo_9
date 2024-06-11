package service.interfaces;

import model.Shift;

import java.util.List;

public interface IShiftService {
    public List<Shift> findShifts();
    public Shift findShiftById(Long id);
    public Shift addShift(Shift shift);
    public Shift deleteShift(Long id);
    public Shift editShift(Long id, Shift shift);
}