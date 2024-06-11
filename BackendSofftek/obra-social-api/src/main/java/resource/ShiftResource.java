package resource;


import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import dto.ShiftDTO;
import service.ShiftService;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Transactional
@Path("/turnos")
public class ShiftResource {

    @Inject
    private ShiftService serviceShift;

    @GET
    @Path("/")
    public Response getShifts(){
        return Response.ok(serviceShift.GetAllShift()).build();
    }

    @GET
    @Path("/{id}")
    public Response getShift(@PathParam("id") Long id){
        try {
            return Response.ok(serviceShift.GetShift(id)).build();
        } catch (Exception e){
            return Response.status(Response.Status.NOT_FOUND).entity(e).build();
        }
    }

    @POST
    public Response addShift(ShiftDTO shift){
        try {
            serviceShift.addShift(shift);
            return Response.ok("se agrego con exito").build();
        }catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateShift(@PathParam("id") Long id, ShiftDTO shift){
        try {
            serviceShift.editShift(id,shift);
            return Response.ok("se actualizo correctamente").build();//PASAR DTO
        }catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteShift(@PathParam("id") Long id) {
        try {
            serviceShift.DeleteShift(id);
            return Response.ok("se elimino con exito").build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

}
