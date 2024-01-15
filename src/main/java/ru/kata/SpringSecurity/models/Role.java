package ru.kata.SpringSecurity.models;

import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "user_role")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rolename", unique = true)
    private String rolename;

    public Role() {}

    public Role(String rolename) {
        this.rolename = rolename;
    }
    @Override
    public String toString() {
        return rolename.replace("ROLE_", "");
    }

    @Override
    public String getAuthority() {
        return getRolename();
    }

    public Long getId() {
        return id;
    }

    public String getRolename() {
        return rolename;
    }
}
