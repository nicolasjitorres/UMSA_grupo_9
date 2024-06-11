package resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Schedule;
import service.interfaces.IScheduleService;

import java.util.List;

@Path("/horarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ScheduleResource
{
    @Inject
    private IScheduleService scheduleService;

    @GET
    public List<Schedule> getSchedules()
    {
        return scheduleService.findSchedules();
    }
    @GET
    @Path("/{id}")
    public Schedule getScheduleById(@PathParam("id") Long id)
    {
        return scheduleService.findScheduleById(id);
    }

    @POST
    public Response addSchedule(Schedule schedule){
        try {
            scheduleService.addSchedule(schedule);
            return Response.status(Response.Status.CREATED).entity(schedule).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteScheduleById(@PathParam("id") Long id)
    {
        try {
            Schedule deletedSchedule = scheduleService.deleteSchedule(id);
            return Response.ok(deletedSchedule).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }

    }
    @PUT
    @Path("/{id}")
    public Response updateSchedule(@PathParam("id") Long id, Schedule schedule)
    {
        try {
            Schedule updatedSchedule = scheduleService.editSchedule(id, schedule);
            return Response.ok(updatedSchedule).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

}
