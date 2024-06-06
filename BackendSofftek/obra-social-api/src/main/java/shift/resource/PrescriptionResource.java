package shift.resource;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import shift.entity.Prescription;
import shift.entity.Shift;
import shift.service.PrescriptionService;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/Recetas")
public class PrescriptionResource {

    @Inject
    PrescriptionService prescriptionService;

    @GET
    @Path("/")
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
    @Path("/agregar-Receta")
    public Response addPrescription(Prescription prescription){
        try{
            prescriptionService.AddPrescription(prescription);
            return Response.ok("se agrego con exito").build();
        }catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}/actualizar-Receta")
    public Response updatePrescription(@PathParam("id") Long id, Prescription prescription){
        try {
            prescriptionService.UpdatePrescription(id,prescription);
            return Response.ok("se actualizo correctamente").build();
        }catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}/eliminar-Receta")
    public Response deletePrescription(@PathParam("id") Long id,Prescription prescription){
        try {
            prescriptionService.DeletePrescription(id,prescription);
            return Response.ok("se elimino con exito").build();
        }catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

}
