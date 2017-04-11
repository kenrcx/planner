package planner.model;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import planner.PasswordHash;

/**
 * Created by Ken on 2017/04/08.
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class User {


    @PrimaryKey
    @Persistent
    private String userId;

    @Persistent
    private String name;

    @Persistent
    private String hashedPassword;

    @Persistent
    private String mailAddress;


    public User(String userId, String name, String mailAddress, String password){
        this.userId = userId;
        this.name = name;
        this.mailAddress = mailAddress;
        this.hashedPassword = PasswordHash.getPasswordHash(userId, password);
    }

    public String getUserId() {
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
