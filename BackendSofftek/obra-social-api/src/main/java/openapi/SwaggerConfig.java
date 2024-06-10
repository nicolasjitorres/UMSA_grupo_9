package openapi;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.servers.Server;
import jakarta.ws.rs.core.Application;


@OpenAPIDefinition(
    info = @Info(
            title = "API de la Obra Social AlMedin",
            version = "1.0.0",
            description = "API para la gestión de turnos médicos, especialistas y recetas médicas."
    ),
    servers = {
        @Server(url = "http://localhost:8080")
    }
)
public class SwaggerConfig extends Application{
}
