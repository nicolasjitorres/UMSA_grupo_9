package resource;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.enums.Speciality;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Path("/enums")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EnumsResource {

    @GET
    @Path("/especialidades")
    public List<String> getSpecialities() {
        return Arrays.stream(Speciality.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}
