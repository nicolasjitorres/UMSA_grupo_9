package shift.service;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@Transactional
@ApplicationScoped
public class ServiceShift {
    public String getShifts(){
        return "yo devuelvo los turnos";
    }
}
