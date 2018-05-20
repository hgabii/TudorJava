package elte.homework.data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="users")
public class User implements Serializable{

    @Id
    private String userName;
    private String password;

    public enum UserType { CLIENT, TUDOR, ADMIN }

    @Enumerated(EnumType.STRING)
    private UserType type;
    private int userId;

    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getType() {
        return this.type;
    }
    public void setType(UserType type) {
        this.type = type;
    }
    public int getUserId() {
        return this.userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public static String getRole(UserType type) { return "ROLE_" + type.name(); }

    @Override
    public String toString() {
        return super.toString();
    }
}
