package resource;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Prescription;
import dto.PrescriptionDTO;
import service.PrescriptionService;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/recetas")
public class PrescriptionResource {

    @Inject
    private PrescriptionService prescriptionService;

    @GET
    public Response getPrescriptions(){
        return Response.ok(prescriptionService.GetAllPrescriptions()).build();
    }

    @GET
    @Path("/{id}")
    public Response getPrescription(@PathParam("id") Long id){
        try {
            return Response.ok(prescriptionService.getPrescription(id)).build();
        } catch (Exception e){
            return Response.status(Response.Status.NOT_FOUND).entity(e).build();
        }

    }

    @POST
    public Response addPrescription(PrescriptionDTO prescriptionDTO){
        try{
            prescriptionService.AddPrescription(prescriptionDTO);
            return Response.ok("se agrego con exito").build();
        }catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updatePrescription(@PathParam("id") Long id, Prescription prescription){
        try {
            prescriptionService.UpdatePrescription(id,prescription);
            return Response.ok("se actualizo correctamente").build();
        }catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletePrescription(@PathParam("id") Long id,Prescription prescription){
        try {
            prescriptionService.DeletePrescription(id,prescription);
            return Response.ok("se elimino con exito").build();
        }catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

}
