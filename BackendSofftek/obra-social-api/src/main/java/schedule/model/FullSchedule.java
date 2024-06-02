package schedule.model;
import java.sql.Timestamp;
public class FullSchedule extends Schedule{
    private Timestamp endTime;
    private Days day;

    public FullSchedule(Long id, Timestamp timeStart) {
        super(id, timeStart);
    }

    public FullSchedule(Long id, Timestamp startTime, Timestamp endTime, Days day) {
        super(id, startTime);
        this.endTime = endTime;
        this.day = day;
    }
}
