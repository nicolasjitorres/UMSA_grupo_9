package shift.resource;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import shift.entity.Shift;
import shift.entity.dto.ShiftDTO;
import shift.service.ShiftService;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/Turnos")
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
    @Path("/agregar-Turno")
    public Response addShift(ShiftDTO shift){
        try {
            serviceShift.AddShift(shift);
            return Response.ok("se agrego con exito").build();
        }catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}/actualizar-Turno")
    public Response updateShift(@PathParam("id") Long id, Shift shift){
        try {
            serviceShift.UpdateShift(id,shift);
            return Response.ok("se actualizo correctamente").build();
        }catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}/eliminar-Turno")
    public Response deleteShift(@PathParam("id") Long id,Shift shift){
        try {
            serviceShift.DeleteShift(id,shift);
            return Response.ok("se elimino con exito").build();
        }catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

}
