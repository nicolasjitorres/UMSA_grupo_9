package shift.resource;


import jakarta.annotation.Resources;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import shift.service.ServiceShift;

@Path("/Shift")
public class ShiftResource {

    @Inject
    ServiceShift serviceShift;

    @GET
    @Path("/getShifts")
    public String getShifts(){
        return serviceShift.getShifts();
    }
}
