package planner.model;

import javax.jdo.annotations.*;

/**
 * Created by Ken on 2017/04/11.
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class EventJoin {

    public EventJoin(String eventId, String userId, boolean isJoin){
        this.eventId = eventId;
        this.userId = userId;
        this.isJoin = isJoin;
    }
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long id;

    @Persistent
    private String eventId;

    @Persistent
    private String userId;

    @Persistent
    private boolean isJoin;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isJoin() {
        return isJoin;
    }

    public void setJoin(boolean join) {
        isJoin = join;
    }
}
