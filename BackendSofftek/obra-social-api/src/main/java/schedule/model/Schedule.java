package schedule.model;

import java.sql.Timestamp;
public class Schedule {
    private Long id;
    private Timestamp timeStart;
    public Schedule(Long id, Timestamp timeStart) {
        this.id = id;
        this.timeStart = timeStart;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Timestamp getTimeStart() {
        return timeStart;
    }
    public void setTimeStart(Timestamp timeStart) {
        this.timeStart = timeStart;
    }
}
