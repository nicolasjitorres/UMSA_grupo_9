package shift.service;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import shift.dao.PrescriptionRepository;
import shift.entity.Prescription;
import shift.entity.Shift;


@Transactional
@ApplicationScoped //esta anotacion se usa para los service
public class PrescriptionService {
    @Inject
    PrescriptionRepository prescriptionRepository;

    public Response GetAllPrescriptions(){
        return Response.ok(prescriptionRepository.findAll().stream().toList()).build();
    }

    public Response GetPrescription(Long idPrescription){
        return Response.ok(prescriptionRepository.findById(idPrescription)).build();
    }

    public Response AddPrescription(Prescription prescription){
        prescriptionRepository.persist(prescription);
        return Response.status(Response.Status.CREATED).entity("se agrego con exito").build();
    }

    public Response UpdatePrescription(Prescription prescription){
        Prescription existingPrescription = prescriptionRepository.findById(prescription.getId());
        if (existingPrescription == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("no se encontro esta receta").build();
        }
        existingPrescription.setDescription(prescription.getDescription());;
        existingPrescription.persist();
        return Response.ok(existingPrescription).build();
    }

    public Response DeletePrescription(Prescription prescription){
        if (prescriptionRepository.findById(prescription.getId()) != null){
            prescriptionRepository.delete(prescription);
            return Response.ok("Se elimino correctamente").build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("no existe esta receta en la base de datos").build();
    }
}
