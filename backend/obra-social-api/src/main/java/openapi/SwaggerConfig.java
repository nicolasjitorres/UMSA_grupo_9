package openapi;

import jakarta.ws.rs.ApplicationPath;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.servers.Server;
import jakarta.ws.rs.core.Application;


@OpenAPIDefinition(
        info = @Info(
                title = "API de la Obra Social AlMedin",
                version = "1.0.0",
                description = "<h3>API para la gestión de turnos médicos, especialistas y recetas médicas.</h3>" +
                        "<p>Proyecto realizado por el <strong>Grupo 9</strong> del curso de la <strong>Academia Java + React 2024</strong>.</p>" +
                        "<h4>Integrantes del grupo:</h4>" +
                        "<ul>" +
                        "<li>Javier Kuznik - <a href=\"mailto:kuznikjavier@gmail.com\">Correo</a> - <a href=https://www.linkedin.com/in/javierkuznik/>LinkedIn</a></li>" +
                        "<li>Nicolás Torres - <a href=\"mailto:NicoMAIL\">Correo</a> - <a href=\"linkedin\">LinkedIn</a></li>" +
                        "<li>Joaquín Muñoz - <a href=\"mailto:joaquinmatias99@gmail.com\">Correo</a> - <a href=https://www.linkedin.com/in/joaquinmuñoz99/>LinkedIn</a></li>" +
                        "</ul>",
                contact = @Contact(
                        name = "Repositorio GITHUB",
                        url = "https://github.com/nicolasjitorres/UMSA_grupo_9"
                )
        ),
        servers = {
                @Server(url = "http://localhost:8080")
        }
)
@ApplicationPath("/")
public class SwaggerConfig extends Application{
}
