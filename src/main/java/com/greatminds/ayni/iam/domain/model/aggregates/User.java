package com.greatminds.ayni.iam.domain.model.aggregates;

import com.greatminds.ayni.iam.domain.model.valueobjects.EmailAddress;
import com.greatminds.ayni.iam.domain.model.valueobjects.Password;
import com.greatminds.ayni.iam.domain.model.valueobjects.Role;
import com.greatminds.ayni.iam.domain.model.valueobjects.Username;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "users")
public class User extends AbstractAggregateRoot<User> {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Username username;

    @Embedded
    private EmailAddress email;

    @Embedded
    private Password password;

    @Embedded
    private Role role;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt;

    public User(String username, String email, String password, String role){
        this.createdAt = new Date();
        this.updatedAt = new Date();
        this.username = new Username(username);
        this.password = new Password(password);
        this.email = new EmailAddress(email);
        this.role = new Role(role);
    }

    public User() {}


    public String getUsername() { return this.username.username(); }
    public String getEmail() { return this.email.email(); }
    public String getPassword() { return this.password.password(); }
    public Set<Role> getRoles() {
        Set<Role> strRoles = new HashSet<>();
        strRoles.add(this.role);
        return strRoles;
    }
}
