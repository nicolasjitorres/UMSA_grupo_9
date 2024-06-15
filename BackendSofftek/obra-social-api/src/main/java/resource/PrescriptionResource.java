package resource;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import dto.PrescriptionDTO;
import dto.mappers.PrescriptionMapper;
import java.util.List;
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
	public Response getAllPrescriptions() {
		List<Prescription> prescriptions = prescriptionService.getAllPrescriptions();
		return Response.ok(prescriptions).build();
	}

	@GET
	@Path("/{id}")
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
	public Response addPrescription(@Valid PrescriptionDTO prescriptionDTO) {
		try{
			return Response.ok(prescriptionService.addPrescription(prescriptionDTO)).build();
		} catch (Exception e){
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

    @PUT
    @Path("/{id}")
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
    public Response deletePrescription(@PathParam("id") Long id){
        try{
			Prescription prescription = prescriptionService.deletePrescription(id);
			return Response.ok(prescription).build();
		} catch (Exception e){
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
    }

}
