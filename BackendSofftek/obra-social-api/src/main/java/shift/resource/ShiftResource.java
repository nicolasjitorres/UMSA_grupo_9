package shift.resource;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import shift.entity.Shift;
import shift.service.ShiftService;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/Shift")
public class ShiftResource {

    @Inject
    ShiftService serviceShift;

    @GET
    @Path("/getShifts")
    public Response getShifts(){
        return serviceShift.GetAllShift();
    }

    @GET
    @Path("/getShift/{id}")
    public Response getShift(@PathParam("id") Long id){
        Response response = null;
        try {
            response = serviceShift.GetShift(id);
        } catch (Exception e){
            response = Response.status(Response.Status.NOT_FOUND).entity(e).build();
        }
        return response;
    }

    @POST
    @Path("/addShift")
    public Response addShift(Shift shift){
        return serviceShift.AddShift(shift);
    }

    @PUT
    @Path("/UpdateShift")
    public Response updateShift(Shift shift){
        return serviceShift.UpdateShift(shift);
    }

    @DELETE
    @Path("/DeleteShift")
    public Response deleteShift(Shift shift){
        return serviceShift.DeleteShift(shift);
    }

}
