package resource;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import dto.PrescriptionDTO;
import mappers.PrescriptionMapper;
import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import service.interfaces.IPrescriptionService;
import model.Prescription;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/recetas")
public class PrescriptionResource {

	@Inject
	private IPrescriptionService prescriptionService;
	@Inject
	private PrescriptionMapper prescriptionMapper;


	@GET
	@Operation(summary = "Obtener todas las recetas", description = "Retorna una lista de todas las recetas.")
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Recetas obtenidas con éxito"),
			@APIResponse(responseCode = "204", description = "No hay recetas disponibles en el sistema")
	})
	public Response getAllPrescriptions() {
		List<Prescription> prescriptions = prescriptionService.getAllPrescriptions();
		if (prescriptions.isEmpty()) {
			return Response.status(Response.Status.NO_CONTENT).build();
		} else {
			return Response.ok(prescriptions).build();
		}
	}

	@GET
	@Path("/{id}")
	@Operation(summary = "Obtener una receta por ID", description = "Retorna una receta basada en el ID proporcionado.")
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Receta obtenida con éxito"),
			@APIResponse(responseCode = "404", description = "Receta no encontrada")
	})
	public Response getPrescriptionById(@PathParam("id") Long id) {
		try {
			Prescription prescription = prescriptionService.getPrescriptionById(id);
			return Response.ok(prescriptionMapper.entityToDto(prescription)).build();
		} catch (Exception e){
			return Response.status(Response.Status.NOT_FOUND)
					.entity("No se encontró prescripción con el id " + id + ".").build();
		}
	}

	@POST
	@Operation(summary = "Agregar una nueva receta medica", description = "Crea una nueva receta medica solo si existe el turno asociado.")
	@APIResponses(value = {
			@APIResponse(responseCode = "201", description = "Receta creada con éxito"),
			@APIResponse(responseCode = "400", description = "Solicitud incorrecta")
	})
	public Response addPrescription(@Valid PrescriptionDTO prescriptionDTO) {
		try{
			Prescription prescription = prescriptionService.addPrescription(prescriptionDTO);
			return Response.ok(prescription).build();
		} catch (Exception e){
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

    @PUT
    @Path("/{id}")
	@Operation(summary = "Actualizar una receta", description = "Actualiza una receta existente basada en el ID proporcionado.")
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Receta actualizada con éxito"),
			@APIResponse(responseCode = "400", description = "Solicitud incorrecta, hay datos invalidos")
			//@APIResponse(responseCode = "404", description = "Receta no encontrada")
	})
    public Response updatePrescription(@PathParam("id") Long id, @Valid PrescriptionDTO prescriptionDTO){
       	try{
			Prescription prescription = prescriptionService.editPrescription(id, prescriptionDTO);
			return Response.ok(prescription).build();
		} catch (Exception e){
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
    }

    @DELETE
    @Path("/{id}")
	@Operation(summary = "Eliminar una receta", description = "Elimina una receta basada en el ID proporcionado.")
	@APIResponses(value = {
			@APIResponse(responseCode = "204", description = "Receta eliminada con éxito"),
			@APIResponse(responseCode = "404", description = "Receta no encontrada")
	})
    public Response deletePrescription(@PathParam("id") Long id){
        try{
			prescriptionService.deletePrescription(id);
			return Response.status(204).build();
		} catch (Exception e){
			return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
		}
    }

}
