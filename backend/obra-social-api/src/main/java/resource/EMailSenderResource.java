//package resource;
//
//import javax.mail.MessagingException;
//
//import jakarta.inject.Inject;
//import jakarta.ws.rs.Consumes;
//import jakarta.ws.rs.POST;
//import jakarta.ws.rs.Path;
//import jakarta.ws.rs.core.MediaType;
//import jakarta.ws.rs.core.Response;
//import org.jboss.logging.Logger;
//import service.EmailService;
//
//@Path("/send-email")
//public class EMailSenderResource {
//
//    @Inject
//    EmailService emailService;
//
//    public static class EmailRequest {
//        public String email;
//        public String message;
//    }
//
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response sendEmail(EmailRequest request) {
//        try {
//            emailService.sendEmail(request.email, "Mensaje desde la aplicación", request.message);
//            return Response.ok("Correo enviado exitosamente").build();
//        } catch (MessagingException e) {
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al enviar el correo: " + e.getMessage()).build();
//        }
//    }
//}
