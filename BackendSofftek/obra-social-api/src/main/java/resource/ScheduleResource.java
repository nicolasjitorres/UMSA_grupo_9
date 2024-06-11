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
        scheduleService.addSchedule(schedule);
            return Response.status(Response.Status.CREATED).entity(schedule).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteScheduleById(@PathParam("id") Long id)
    {
        Schedule schedule = scheduleService.findScheduleById(id);
        if(schedule!=null)
        {
            return Response.ok().entity("Schedule deleted successfully").build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).entity("Schedule not found").build();
        }

    }
    @PUT
    @Path("/{id}")
    public Response updateSchedule(@PathParam("id") Long id, Schedule schedule)
    {
        Schedule existingSchedule = scheduleService.findScheduleById(id);
        if (existingSchedule!=null)
        {
            scheduleService.editSchedule(id, schedule);
            schedule.setId(id);
            return Response.ok(schedule).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}