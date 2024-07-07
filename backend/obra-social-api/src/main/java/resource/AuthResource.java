package resource;

import java.util.Optional;
import java.util.Set;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.User;
import security.PassHashing;
import security.TokenJWT;
import security.UserCredentials;
import service.UserService;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    UserService userService;

    @POST
    public Response login(UserCredentials credentials) {
        Optional<User> optionalUser = userService.findByEmail(credentials.getEmail());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (PassHashing.verifyPassword(credentials.getPassword(), user.getPassword())) {
                Set<String> role = Set.of(user.getRole().name());
                String token = TokenJWT.generateToken(user.getEmail(), role);
                return Response.ok("Logueado exitosamente! Token: " + token).build();
            }
        }

        return Response.status(Response.Status.UNAUTHORIZED).entity("Credenciales invalidas.").build();
    }

}
