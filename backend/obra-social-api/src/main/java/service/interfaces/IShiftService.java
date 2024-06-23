package service.interfaces;

import dto.ShiftDTO;
import jakarta.enterprise.context.ApplicationScoped;
import model.Shift;

import java.util.List;

@ApplicationScoped
public interface IShiftService {
    public List<Shift> getAllShifts();
    public Shift getShiftById(Long id);
    public List<Shift> getShiftBySpecialistId(Long idSpecialist);
    public List<Shift> getShiftByAffiliateId(Long idAffiliate);
    public Shift addShift(ShiftDTO shiftDTO) throws Exception;
    public Shift deleteShift(Long id) throws Exception;
    public Shift editShift(Long id, ShiftDTO shiftDTO) throws Exception;
}