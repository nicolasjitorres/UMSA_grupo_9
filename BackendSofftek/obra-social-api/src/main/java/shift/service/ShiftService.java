package shift.service;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import shift.dao.ShiftRepository;
import shift.entity.Shift;

@Transactional
@ApplicationScoped //esta anotacion se usa para los service
public class ShiftService {

    @Inject
    ShiftRepository shiftRepository;

    public Response GetAllShift(){
        return Response.ok(shiftRepository.findAll().stream().toList()).build();
    }

    public Response GetShift(Long idShift){
        return Response.ok(shiftRepository.findById(idShift)).build();
    }

    public Response AddShift(Shift shift){
        if(shiftRepository.findByDateAndHour(shift.getDate(),shift.getHour()) != null){
            return Response.status(Response.Status.BAD_REQUEST).entity("ya existe este turno").build();
        }
        shiftRepository.persist(shift);
        return Response.status(Response.Status.CREATED).entity("se agrego con exito").build();
    }

    public Response UpdateShift(Shift shift){
        Shift existingShift = shiftRepository.findById(shift.getId());
        if (existingShift == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingShift.setDescription(shift.getDescription());;
        existingShift.setDate(shift.getDate());
        existingShift.setHour(shift.getHour());
        existingShift.setState(shift.getState());
        existingShift.persist();
        return Response.ok(existingShift).build();
    }

    public Response DeleteShift(Shift shift){
        Shift existingShift = shiftRepository.findById(shift.getId());

        if ( existingShift != null){
            if(existingShift.getPrescription() == null){
                shiftRepository.delete(shift);
                return Response.ok("Se elimino correctamente").build();
            }else{
                return Response.status(Response.Status.BAD_REQUEST).entity("no se puede eliminar una turno que tiene asociado una receta, elimine la receta con el id: "+existingShift.getPrescription().getId()+" primero").build();
            }

        }
        return Response.status(Response.Status.NOT_FOUND).entity("no existe este turno en la base de datos").build();
    }

}
