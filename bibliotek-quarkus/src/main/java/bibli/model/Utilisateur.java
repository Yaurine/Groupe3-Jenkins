package bibli.model;

import org.hibernate.annotations.UuidGenerator;

import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@UserDefinition
public class Utilisateur {
    @Id
    @UuidGenerator
    private String id;

    @Username
    @Column(length = 50, nullable = false)
    private String username;

    @Password
    @Column(length = 150, nullable = false)
    private String password;

    private boolean admin;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return this.admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Roles
    public String getRole() {
        return (this.admin) ? "admin" : "user";
    }

}
