package planner.model;

import javax.jdo.annotations.*;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.datanucleus.annotations.Unowned;

/**
 * Created by Ken on 2017/04/11.
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class EventJoin {

    public EventJoin(User user, Event event ,boolean isJoin) {
        this.user = user;
        this.event = event;
        this.isJoin = isJoin;
    }

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key id;

    @Persistent
    @Unowned
    private Event event;

    @Persistent
    @Unowned
    private User user;

    @Persistent
    private boolean isJoin;

    public Key getId() {
        return id;
    }




    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isJoin() {
        return isJoin;
    }

    public void setJoin(boolean join) {
        isJoin = join;
    }



}
