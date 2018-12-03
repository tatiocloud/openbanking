package io.ram.openbanking.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Temporal(TemporalType.DATE)
    private Date creationDt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLoggedIn;

    @Column(name = "is_currently_logged_in")
    private boolean status;

    public User(){
        //hibernate
    }

    public User(String username, String password, Date createDt, Timestamp lastLoggedIn) {
        this.username = username;
        this.password = password;
        this.creationDt = creationDt;
        this.lastLoggedIn = lastLoggedIn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastLoggedIn() {
        return lastLoggedIn;
    }

    public void setLastLoggedIn(Timestamp lastLoggedIn) {
        this.lastLoggedIn = lastLoggedIn;
    }

    public void loggin(){
        this.status = true;
    }

    public boolean getStatus(){
        return this.status;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof User))
            return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) &&
               Objects.equals(getUsername(), user.getUsername());
    }

    @Override public int hashCode() {
        return Objects.hash(getId(), getUsername());
    }

    @Override public String toString() {
        return "User{" +
               "id=" + id +
               ", username='" + username + '\'' +
               ", password='" + password + '\'' +
               ", lastLoggedIn=" + lastLoggedIn +
               ", currentlyLoggedIn=" + status +
               '}';
    }
}
