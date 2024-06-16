package resource;


import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Shift;
import dto.ShiftDTO;
import dto.mappers.ShiftMapper;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import service.interfaces.IShiftService;

import java.util.List;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/turnos")
@Tag(name = "Turnos")
public class ShiftResource {

    @Inject
    private IShiftService iserviceShift;

	@GET
    @Path("/")
    @Operation(summary = "Obtener todos los turnos", description = "Retorna una lista de todos los turnos de la obra social.")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Turnos obtenidos con éxito"),
            @APIResponse(responseCode = "204", description = "No hay turnos cargados cargados en el sistema")
    })
    public Response getShifts(){
        List<Shift> shiftList  = iserviceShift.getAllShifts();
        if(shiftList.isEmpty())
            return Response.status(204).build();
        else {
            return Response.ok(shiftList).build();
        }
    }
    @GET
    @Path("/{id}")
    @Operation(summary = "Obtener un turno por ID", description = "Retorna un turno basado en el ID proporcionado.")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Turno obtenido con éxito"),
            @APIResponse(responseCode = "404", description = "Turno no encontrado")
    })
    public Response getShift(@PathParam("id") Long id){
        Shift shift = iserviceShift.getShiftById(id);
        if(shift==null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }else {
            return Response.ok(shift).build();
        }
    }

    @POST
    @Operation(summary = "Agregar un turno", description = "Se debe ingresar la id del especialista y afiliado en cuestión, los cuales ya deben de estar cargados en el sistema. No se permiten ingresar campos vacios.")
    @APIResponse(responseCode = "200", description = "Turno agregado con éxito")
    @APIResponse(responseCode = "400", description = "Solicitud incorrecta, hay algún dato mal ingresado")
    public Response addShift(@Valid ShiftDTO shiftDto){
        try {
        	Shift newShift = iserviceShift.addShift(shiftDto);
            return Response.ok(newShift).build();
        }catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Actualizar un turno", description = "Se debe ingresar la id del turno, especialista y afiliado en cuestión, los cuales ya deben de estar cargados en el sistema. No se permiten ingresar campos vacios.")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Turno actualizado con éxito"),
            @APIResponse(responseCode = "400", description = "Solicitud incorrecta, error en algún parametro"),
            @APIResponse(responseCode = "404", description = "Solicitud incorrecta, turno no encontrado")
    })
    public Response updateShift(@PathParam("id") Long id,@Valid ShiftDTO shiftDto){
        try {
            return Response.ok("se actualizo correctamente").entity(iserviceShift.editShift(id,shiftDto)).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Eliminar un turno", description = "Elimina un turno existente. Se debe ingresar la id de un turno existente")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Turno eliminado con éxito"),
            @APIResponse(responseCode = "404", description = "Solicitud incorrecta, no existe dicho turno")
    })
    public Response deleteShift(@PathParam("id") Long id) {
        try {
            return Response.ok("se elimino con exito").entity(iserviceShift.deleteShift(id)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
