package shift.resource;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import shift.entity.Prescription;
import shift.service.PrescriptionService;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/Prescription")
public class PrescriptionResource {

    @Inject
    PrescriptionService prescriptionService;

    @GET
    @Path("/getPrescriptions")
    public Response getPrescriptions(){
        return prescriptionService.GetAllPrescriptions();
    }

    @GET
    @Path("/getPrescription/{id}")
    public Response getPrescription(@PathParam("id") Long id){
        return prescriptionService.GetPrescription(id);
    }

    @POST
    @Path("/addPrescription")
    public Response addPrescription(Prescription prescription){
        return prescriptionService.AddPrescription(prescription);
    }

    @PUT
    @Path("/UpdatePrescription")
    public Response updatePrescription(Prescription prescription){
        return prescriptionService.UpdatePrescription(prescription);
    }

    @DELETE
    @Path("/DeletePrescription")
    public Response deletePrescription(Prescription prescription){
        return prescriptionService.DeletePrescription(prescription);
    }

}
