package resource;


import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Shift;
import dto.ShiftDTO;
import dto.mappers.ShiftMapper;
import service.interfaces.IShiftService;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Transactional
@Path("/turnos")
public class ShiftResource {

    private IShiftService iserviceShift;
    private ShiftMapper shiftMapper;

    @Inject
    public ShiftResource(IShiftService iserviceShift, ShiftMapper shiftMapper) {
		super();
		this.iserviceShift = iserviceShift;
		this.shiftMapper = shiftMapper;
	}

	@GET
    @Path("/")
    public Response getShifts(){
        return Response.ok(iserviceShift.getAllShifts()).build();
    }

    @GET
    @Path("/{id}")
    public Response getShift(@PathParam("id") Long id){
        try {
            return Response.ok(iserviceShift.getShiftById(id)).build();
        } catch (Exception e){
            return Response.status(Response.Status.NOT_FOUND).entity(e).build();
        }
    }

    @POST
    public Response addShift(ShiftDTO shiftDto){
        try {
        	Shift newShift = iserviceShift.addShift(shiftMapper.createShiftDto(shiftDto));
            return Response.ok(shiftMapper.entityToDto(newShift)).build();
        }catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateShift(@PathParam("id") Long id, ShiftDTO shiftDto){
        try {
        	Shift shift = shiftMapper.createShiftDto(shiftDto);
            return Response.ok("se actualizo correctamente").entity(iserviceShift.editShift(id,shift)).build();
        }catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteShift(@PathParam("id") Long id) {
        try {
            return Response.ok("se elimino con exito").entity(iserviceShift.deleteShift(id)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
