package planner.model;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import java.util.Date;

/**
 * Created by Ken on 2017/04/09.
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Event {

    public Event(String eventId, String title, Date date, int length, String description, String eventOwner, boolean isInvestigating, Date investigationDeadLine) {
        this.eventId = eventId;
        this.title = title;
        this.date = date;
        this.length = length;
        this.description = description;
        this.eventOwner = eventOwner;
        this.isInvestigating = isInvestigating;
        this.investigationDeadLine = investigationDeadLine;
    }

    @PrimaryKey
    @Persistent
    private String eventId;

    @Persistent
    private String title;

    @Persistent
    private Date date;

    @Persistent
    private int length;

    @Persistent
    private String description;


    @Persistent
    private String eventOwner;

    @Persistent
    private boolean isInvestigating;

    @Persistent
    private Date investigationDeadLine;


    public String getEventId() {
        return eventId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEventOwner() {
        return eventOwner;
    }

    public boolean isInvestigating() {
        return isInvestigating;
    }

    public void setInvestigating(boolean investigating) {
        isInvestigating = investigating;
    }

    public Date getInvesigationDeadLine() {
        return investigationDeadLine;
    }

    public void setInvestigationDeadLine(Date investigationDeadLine) {
        this.investigationDeadLine = investigationDeadLine;
    }


}
