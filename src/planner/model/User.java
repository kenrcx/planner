package planner.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import com.google.appengine.api.datastore.Key;

/**
 * Created by Ken on 2017/04/08.
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class User {


    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key userId;

    @Persistent
    private String name;

    @Persistent
    private String hashedPassword;

    @Persistent
    private String mailAddress;


    public User(String name, String mailAddress){
        this.name = name;
        this.mailAddress = mailAddress;
    }

    public Key getUserId() {
        return userId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }
}
