package planner.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.datanucleus.annotations.Unowned;

/**
 * Created by Ken on 2017/04/09.
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Event {

    public Event(String title, Date date, int length, String description, User eventOwner, boolean isInvestigating, Date investigationDeadLine) {
        this.title = title;
        this.date = date;
        this.length = length;
        this.description = description;
        this.eventOwner = eventOwner;
        this.isInvestigating = isInvestigating;
        this.investigationDeadLine = investigationDeadLine;
        this.eventjoins = new ArrayList<>();
    }

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key eventId;

    @Persistent
    private String title;

    @Persistent
    private Date date;

    @Persistent
    private int length;

    @Persistent
    private String description;


    @Persistent
    @Unowned
    private User eventOwner;

    @Persistent
    private boolean isInvestigating;

    @Persistent
    private Date investigationDeadLine;

    @Persistent(mappedBy = "event", defaultFetchGroup = "true")
    @Element(dependent = "true")
    @Unowned
    private List<EventJoin> eventjoins;


    public Key getEventId() {
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

    public User getEventOwner() {
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

    public List<EventJoin> getEventjoins(){
        return this.eventjoins;
    }

    public void addEventJoins(EventJoin eventJoin){
        this.eventjoins.add(eventJoin);
    }


}
