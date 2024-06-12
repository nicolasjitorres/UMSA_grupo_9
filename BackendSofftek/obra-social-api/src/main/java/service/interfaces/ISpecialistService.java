package service.interfaces;

import model.Specialist;

import java.util.List;

public interface ISpecialistService {
    public List<Specialist> getAllSpecialists();
    public Specialist getSpecialistById(Long id);
    public Specialist addSpecialist(Specialist specialist);
    public Specialist deleteSpecialist(Long id);
    public Specialist editSpecialist(Long id, Specialist specialist);
}