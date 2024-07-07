package service.interfaces;

import jakarta.enterprise.context.ApplicationScoped;
import model.Specialist;

import java.util.List;

import dto.SpecialistDTO;

@ApplicationScoped
public interface ISpecialistService {
    public List<Specialist> getAllSpecialists();
    public Specialist getSpecialistById(Long id);
    public Specialist addSpecialist(SpecialistDTO specialistDto) throws Exception ;
    public Specialist deleteSpecialist(Long id) throws Exception;
    public Specialist editSpecialist(Long id, Specialist specialist) throws Exception;
}