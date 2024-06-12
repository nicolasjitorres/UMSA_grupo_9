package resource;

import jakarta.inject.Inject;
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

	private IPrescriptionService prescriptionService;
	private PrescriptionMapper prescriptionMapper;

	@Inject
	public PrescriptionResource(IPrescriptionService prescriptionService, PrescriptionMapper prescriptionMapper) {
		super();
		this.prescriptionService = prescriptionService;
		this.prescriptionMapper = prescriptionMapper;
	}

	@GET
	public Response getAllPrescriptions() {
		List<Prescription> prescriptions = prescriptionService.getAllPrescriptions();
		return Response.ok(prescriptions).build();
	}

	@GET
	@Path("/{id}")
	public Response getPrescriptionById(@PathParam("id") Long id) {

		Prescription prescription = prescriptionService.getPrescriptionById(id);
		if (prescription != null) {
			return Response.ok(prescriptionMapper.entityToDto(prescription)).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("No se encontró prescripción con el id " + id + ".").build();
		}
	}

	@POST
	public Response addPrescription(PrescriptionDTO prescriptionDTO) {
		Prescription prescription = prescriptionService
				.addPrescription(prescriptionMapper.dtoToEntity(prescriptionDTO));
		if (prescription != null) {
			return Response.ok(prescription).build();
		} else {
			return Response.status(Response.Status.BAD_REQUEST).entity("No se pudo agregar la prescripción.").build();
		}
	}

    @PUT
    @Path("/{id}")
    public Response updatePrescription(@PathParam("id") Long id, PrescriptionDTO prescriptionDTO){
        Prescription prescription = prescriptionService.editPrescription(id, prescriptionMapper.dtoToEntity(prescriptionDTO));
        if (prescription != null) {
			return Response.ok(prescription).build();
		} else {
			return Response.status(Response.Status.BAD_REQUEST).entity("No se pudo editar la prescripción.").build();
		}
    }

    @DELETE
    @Path("/{id}")
    public Response deletePrescription(@PathParam("id") Long id){
    	Prescription prescription = prescriptionService.deletePrescription(id);
        if (prescription != null) {
			return Response.ok(prescription).build();
		} else {
			return Response.status(Response.Status.BAD_REQUEST).entity("No se pudo eliminar la prescripción.").build();
		}
    }

}
